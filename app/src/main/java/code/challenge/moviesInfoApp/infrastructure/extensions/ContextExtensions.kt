package code.challenge.moviesInfoApp.infrastructure.extensions

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import java.net.HttpURLConnection
import java.net.URL
import code.challenge.moviesInfoApp.BuildConfig.movie_api_url
import code.challenge.moviesInfoApp.BuildConfig.movie_picture_url
import code.challenge.moviesInfoApp.BuildConfig.api_key
import code.challenge.moviesInfoApp.BuildConfig.api_key_type


private val customLifecycle = object : Lifecycle() {
    override fun addObserver(observer: LifecycleObserver) {}

    override fun removeObserver(observer: LifecycleObserver) {}

    override fun getCurrentState() = State.CREATED
}

fun Context.checkNetworkConnection(): Boolean {
    return try {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        cm?.isDefaultNetworkActive ?: false
    } catch (e: Exception) {
        false
    }
}

fun Context.getLifecycle(): Lifecycle {
    return when (this) {
        is AppCompatActivity -> this.lifecycle
        else -> customLifecycle
    }
}

fun isInternetAccessible(): Boolean {
    return try {
        val urlc = URL("http://www.google.com").openConnection() as HttpURLConnection
        urlc.setRequestProperty("User-Agent", "Test")
        urlc.setRequestProperty("Connection", "close")
        urlc.connectTimeout = 1500
        urlc.connect()
        urlc.responseCode == 200
    } catch (e: Exception) {
        false
    }
}

fun buildMovieServiceUrl() = movie_api_url
fun buildMoviePictureUrl() = movie_picture_url
fun buildApiAccessKey() = "$api_key_type $api_key"



