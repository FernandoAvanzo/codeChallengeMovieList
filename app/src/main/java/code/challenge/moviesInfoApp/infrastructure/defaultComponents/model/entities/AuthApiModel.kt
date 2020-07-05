package code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities

import code.challenge.moviesInfoApp.infrastructure.extensions.buildApiAccessKey
import code.challenge.moviesInfoApp.infrastructure.extensions.buildMovieServiceUrl

data class AuthApiModel(val url: String = buildMovieServiceUrl(),
                        val token:String = buildApiAccessKey()
)