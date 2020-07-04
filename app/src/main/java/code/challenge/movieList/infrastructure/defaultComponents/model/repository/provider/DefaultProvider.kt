package code.challenge.movieList.infrastructure.defaultComponents.model.repository.provider

import android.content.Context
import code.challenge.movieList.infrastructure.defaultComponents.network.RestProvider

abstract class DefaultProvider<T : DefaultServiceAPI>(context: Context, url: String? = "") :
    RestProvider(context, url) {

    val service: T by lazy { retrofit.create(loadServiceClass()) }

    abstract fun loadServiceClass(): Class<T>
}