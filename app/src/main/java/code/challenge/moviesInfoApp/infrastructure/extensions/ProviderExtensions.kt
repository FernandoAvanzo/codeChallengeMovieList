package code.challenge.moviesInfoApp.infrastructure.extensions

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.RequestCallback
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.RestProvider
import retrofit2.Response

fun <T> RestProvider.defaultCallback(
    loader: (ComunicationProtocolModel) -> Any,
    context: Context
): RequestCallback<T> {
    val comunication = ComunicationProtocolModel()
    comunication.load = true
    loader(comunication)
    return object : RequestCallback<T>(context.getLifecycle()) {
        override fun onSuccess(response: T) {
            super.onSuccess(response)
            comunication.result = response
            comunication.load = false
            comunication.isError = false
            comunication.responseCode = 200
            loader(comunication)
        }

        override fun onFailure(message: String, response: Response<T>) {
            super.onFailure(message, response)
            comunication.load = false
            comunication.isError = true
            comunication.responseCode = response.code()
            comunication.message = message
            loader(comunication)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            comunication.load = false
            comunication.isError = true
            comunication.responseCode = 500
            comunication.message = ""
            loader(comunication)
        }
    }
}
