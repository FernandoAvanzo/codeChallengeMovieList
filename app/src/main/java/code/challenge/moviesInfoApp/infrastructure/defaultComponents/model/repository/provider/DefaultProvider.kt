package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.RestProvider
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl

abstract class DefaultProvider<T : DefaultServiceAPI>(
    context: Context,
    url: String? = buildMovieServiceUrl()
) :
    RestProvider(context, url) {

    val service: T by lazy { retrofit.create(loadServiceClass()) }

    abstract fun loadServiceClass(): Class<T>
}