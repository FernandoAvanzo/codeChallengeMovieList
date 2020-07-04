package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface ListOfMoviesService {

    @GET("movie/upcoming")
    fun loadUpComingMoviesService(): Single<Response<ListOfMoviesPresenter>>
}