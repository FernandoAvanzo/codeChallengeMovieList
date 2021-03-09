package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.AuthApiModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultProvider
import code.challenge.moviesInfoApp.infrastructure.extensions.defaultCallback
import code.challenge.moviesInfoApp.infrastructure.network.setCallback

class ListOfMoviesProvider(
    auth: AuthApiModel = AuthApiModel()
) : DefaultProvider<ListOfMoviesService>(auth) {

    override fun loadServiceClass() = ListOfMoviesService::class.java

    fun loadUpComingMovies(page:Int = 1) {
        service.loadUpComingMoviesService("pt-BR",page)
            .setCallback(defaultCallback())
    }

    fun loadPosterPicture(posterPath: String) {
        service.loadPosterPictureService(posterPath.replace("/",""))
            .setCallback(defaultCallback(posterPath))
    }
}