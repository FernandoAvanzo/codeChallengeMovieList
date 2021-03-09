package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import android.content.Context
import code.challenge.moviesInfoApp.MovieInfoApp
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.viewmodels.ViewModelDelegator.currentDelegator
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.viewmodels.ViewModelDelegator.serviceHost

interface PresenterDelegator {

    fun baseContext(): Context = MovieInfoApp.APP
    fun customErrorBehavior(comunication: ComunicationProtocolModel) {}
    fun customLoaderBehavior(isLoading: Boolean)
    fun defaultLoader(comunication: ComunicationProtocolModel) {}
    fun registerCurrentDelegator(delegator: PresenterDelegator) = currentDelegator(delegator)
    fun registerServiceHost(host: String) = serviceHost(host)
}