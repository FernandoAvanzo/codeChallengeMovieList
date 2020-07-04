package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.repository.provider.DefaultServiceAPI
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMovies
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ListOfMoviesService: DefaultServiceAPI {

    @GET("/movie/upcoming")
    fun loadUpComingMoviesService(): Single<Response<ListOfMovies>>
}