package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultProvider
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.setCallback
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl
import code.challenge.moviesInfoApp.infrastructure.extensions.defaultCallback
import code.challenge.moviesInfoApp.infrastructure.extensions.loaderHelper

class ListOfMoviesProvider(
    context: Context,
    private val loader: ((ComunicationProtocolModel) -> Any) = loaderHelper,
    url: String = buildMovieServiceUrl()
) : DefaultProvider<ListOfMoviesService>(context, url) {

    override fun loadServiceClass() = ListOfMoviesService::class.java

    fun loadUpComingMovies() {
        service.loadUpComingMoviesService()
            .setCallback(defaultCallback(loader, context))
    }

    fun loadPosterPicture(posterPath: String) {
        service.loadPosterPictureService(posterPath)
            .setCallback(defaultCallback(loader, context))
    }
}