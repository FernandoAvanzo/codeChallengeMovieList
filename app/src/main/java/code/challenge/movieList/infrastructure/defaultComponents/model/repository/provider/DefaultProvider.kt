package code.challenge.movieList.infrastructure.defaultComponents.model.repository.provider

import android.content.Context
import code.challenge.movieList.infrastructure.defaultComponents.network.RestProvider

abstract class DefaultProvider<S : DefaultServiceAPI>(
    val providerContext: Context,
    url: String? = ""
) : RestProvider(providerContext, url) {

    val service: S by lazy { retrofit.create(loadServiceClass()) }

    abstract fun loadServiceClass(): Class<S>
}