package code.challenge.moviesInfoApp.infrastructure.extensions

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.DefaultPresenter
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.viewmodels.ViewModelDelegator

val loaderHelper = { _: ComunicationProtocolModel -> }

fun emptyDefaultPresenter() = object : DefaultPresenter(emptyDefaultView()) {
    override fun customSuccesBehavior(result: Any?, request: Any?) {}
}

fun getServiceHost(): String =
    ViewModelDelegator.getServiceHost()

fun currentLoader(): (ComunicationProtocolModel) -> Any =
    ViewModelDelegator.getDefaultLoader()

fun currentContext(): Context =
    ViewModelDelegator.getCurrentContext()

