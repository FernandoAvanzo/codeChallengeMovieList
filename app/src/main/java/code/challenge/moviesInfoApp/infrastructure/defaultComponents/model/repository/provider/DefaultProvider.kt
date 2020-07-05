package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.RestProvider
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl

abstract class DefaultProvider<T>(
    context: Context,
    url: String = buildMovieServiceUrl()
) : RestProvider(context, url) {

    val service: T get() = retrofit.create(this.loadServiceClass())

    abstract fun loadServiceClass(): Class<T>
}