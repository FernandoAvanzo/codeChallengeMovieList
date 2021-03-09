package code.challenge.moviesInfoApp.infrastructure.extensions

import code.challenge.moviesInfoApp.BuildConfig
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.network.RequestCallback
import retrofit2.Response

fun <T> defaultCallback(request: Any = Any()): RequestCallback<T> = ComunicationProtocolModel().let {
    it.load = true
    currentLoader()(it)
    object : RequestCallback<T>(currentContext().getLifecycle()) {
        override fun onSuccess(response: T) {
            super.onSuccess(response)
            it.result = response
            it.request = request
            it.load = false
            it.isError = false
            it.responseCode = 200
            currentLoader()(it)
        }

        override fun onFailure(message: String, response: Response<T>) {
            super.onFailure(message, response)
            it.load = false
            it.isError = true
            it.request = request
            it.responseCode = response.code()
            it.message = message
            currentLoader()(it)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            it.load = false
            it.isError = true
            it.request = request
            it.responseCode = 500
            it.message = ""
            currentLoader()(it)
        }
    }
}

fun buildBaseUrl(): String = BuildConfig.movie_api_url
