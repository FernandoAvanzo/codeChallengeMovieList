package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.AuthApiModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultProvider
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.setCallback
import code.challenge.moviesInfoApp.infrastructure.extensions.defaultCallback
import code.challenge.moviesInfoApp.infrastructure.extensions.loaderHelper

class ListOfMoviesProvider(
    context: Context,
    private val loader: ((ComunicationProtocolModel) -> Any) = loaderHelper,
    auth: AuthApiModel = AuthApiModel()
) : DefaultProvider<ListOfMoviesService>(context, auth) {

    override fun loadServiceClass() = ListOfMoviesService::class.java

    fun loadUpComingMovies(page:Int = 1) {
        service.loadUpComingMoviesService("pt-BR",page)
            .setCallback(defaultCallback(loader, context))
    }

    fun loadPosterPicture(posterPath: String) {
        service.loadPosterPictureService(posterPath.replace("/",""))
            .setCallback(defaultCallback(loader, context, posterPath))
    }
}