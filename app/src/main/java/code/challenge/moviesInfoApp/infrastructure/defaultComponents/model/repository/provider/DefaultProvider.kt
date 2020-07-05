package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider

import android.content.Context
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.AuthApiModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.network.RestProvider
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl

abstract class DefaultProvider<T>(
    context: Context,
    auth: AuthApiModel = AuthApiModel()
) : RestProvider(context, auth) {

    val service: T get() = retrofit.create(this.loadServiceClass())

    abstract fun loadServiceClass(): Class<T>
}