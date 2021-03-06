package code.challenge.moviesInfoApp.infrastructure.network

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import code.challenge.moviesInfoApp.BuildConfig
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.AuthApiModel
import code.challenge.moviesInfoApp.infrastructure.extensions.checkNetworkConnection
import code.challenge.moviesInfoApp.infrastructure.extensions.isInternetAccessible
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

open class RestProvider(val context: Context, auth: AuthApiModel = AuthApiModel()) {

    companion object {
        private val LOGGING_LEVEL: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
        private const val TIMEOUT_SECONDS = 500L
    }

    val retrofit: Retrofit

    private val token by lazy { auth.token }

    private val mAuthInterceptor by lazy {
        Interceptor { chain ->
            val original = chain.request() as Request

            return@Interceptor when (token.isNotEmpty()) {
                true -> {
                    val request = original.newBuilder()
                        .header("Authorization", token)
                        .build() as Request
                    chain.proceed(request) as Response
                }
                else -> {
                    val request = original.newBuilder()
                        .build() as Request
                    chain.proceed(request) as Response
                }
            }
        }
    }

    init {
        val logInterceptor = HttpLoggingInterceptor().apply {
            level = LOGGING_LEVEL
        }

        val clientBuilder = OkHttpClient()
            .newBuilder()
            .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(ConnectivityAwareInterceptor(context))
            .addInterceptor(mAuthInterceptor)
            .addInterceptor(logInterceptor)
            .retryOnConnectionFailure(true)
            .connectionPool(ConnectionPool(15, TIMEOUT_SECONDS, TimeUnit.SECONDS))

        val client = clientBuilder.build()

        val gson: Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        retrofit = Retrofit
            .Builder()
            .baseUrl(auth.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }
}

class ConnectivityAwareInterceptor(private val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.checkNetworkConnection() && !isInternetAccessible()) throw NoConnectivityException()
        return chain.proceed(chain.request())
    }
}

private fun formatFailureMessage(message: String?): String {
    return try {
        val msg = "Error Parsing JSON"
        message ?: msg
    } catch (e: Exception) {
        val msg = e.message ?: "Error Parsing JSON"
        msg
    }
}

private fun handleNetworkError(throwable: Throwable) {
    if (BuildConfig.DEBUG) Log.e("RequestError", throwable.message, throwable)

}

fun <Request, Response : retrofit2.Response<Request>> Single<Response>.setCallback(callback: RequestCallback<Request>) {
    subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError(::handleNetworkError)
        .doFinally(callback::onComplete)
        .subscribe({ response ->
            val body = response.body()
            if (response.isSuccessful && body != null) {
                callback.onSuccess(body)
            } else {
                val failureMessage =
                    if (response.code() in 500..599) ""
                    else formatFailureMessage(response.errorBody()?.string())
                callback.onFailure(failureMessage, response)
            }
        }, callback::onError)
        .disposeOnStopEvent(callback.lifecycle)
}

private fun Disposable.disposeOnStopEvent(lifecycle: Lifecycle) {
    lifecycle.addObserver(object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun cancelCalls() = dispose()
    })
}

class NoConnectivityException : IOException("No connectivity")