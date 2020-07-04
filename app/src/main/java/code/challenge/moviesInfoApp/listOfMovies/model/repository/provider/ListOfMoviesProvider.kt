package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultProvider

class ListOfMoviesProvider(context: Context, url: String? = "")
    : DefaultProvider<ListOfMoviesService>(context,url) {

    override fun loadServiceClass() = ListOfMoviesService::class.java
}