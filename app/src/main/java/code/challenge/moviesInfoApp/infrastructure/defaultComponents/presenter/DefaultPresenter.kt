package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView

abstract class DefaultPresenter(val view: DefaultView) {

    var flowErrorControl = -1

    init {
        flowErrorControl = -1
    }

    abstract fun customSuccesBehavior(result: Any?)

    fun defaultLoader(comunication: ComunicationProtocolModel) {
        comunication.load?.let {
            when (it) {
                true -> customLoaderBehavior(it)
                else -> {
                    customLoaderBehavior(it)
                    when (comunication.isError) {
                        true -> customErrorBehavior(comunication)
                        else -> customSuccesBehavior(comunication.result)
                    }
                }
            }
        }
    }

    private fun customLoaderBehavior(isLoading: Boolean) {
        when (isLoading) {
            true -> view.showLoading()
            else -> view.hideLoading()
        }
    }

    private fun customErrorBehavior(comunication: ComunicationProtocolModel) {
         view.onFailure(comunication)
         view.onError(comunication)
    }

}
