package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.AuthApiModel
import code.challenge.moviesInfoApp.infrastructure.extensions.currentContext
import code.challenge.moviesInfoApp.infrastructure.network.RestProvider

abstract class DefaultProvider<T>(
    auth: AuthApiModel = AuthApiModel()
) : RestProvider(currentContext(), auth) {

    val service: T get() = retrofit.create(this.loadServiceClass())

    abstract fun loadServiceClass(): Class<T>
}