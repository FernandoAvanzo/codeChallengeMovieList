package code.challenge.moviesInfoApp.infrastructure.network

import androidx.lifecycle.Lifecycle
import retrofit2.Response

abstract class DefaultRequestCallback (var lifecycle: Lifecycle) {
    internal open fun onError(throwable: Throwable) {}
    internal open fun onComplete() {}
}

abstract class RequestCallback<T>(lifecycle: Lifecycle) :
    DefaultRequestCallback(lifecycle) {
    internal open fun onSuccess(response: T) {}
    internal open fun onFailure(message: String, response: Response<T>) {}
}

abstract class RequestWrapper(private val original: DefaultRequestCallback) :
    DefaultRequestCallback(original.lifecycle) {
    override fun onComplete() = original.onComplete()
    override fun onError(throwable: Throwable) = original.onError(throwable)
}
