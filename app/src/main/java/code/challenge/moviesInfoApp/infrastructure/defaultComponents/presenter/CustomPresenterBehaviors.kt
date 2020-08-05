package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel

interface CustomPresenterBehaviors {

    fun customErrorBehavior(comunication: ComunicationProtocolModel){}
    fun customLoaderBehavior(isLoading: Boolean)
}