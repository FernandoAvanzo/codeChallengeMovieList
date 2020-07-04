package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultProvider
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl

class ListOfMoviesProvider(context: Context, url: String? = buildMovieServiceUrl()) :
    DefaultProvider<ListOfMoviesService>(context, url) {

    override fun loadServiceClass() = ListOfMoviesService::class.java
}