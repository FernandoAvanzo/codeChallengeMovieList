package code.challenge.moviesInfoApp.listOfMovies.model.repository.provider

import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface ListOfMoviesService {

    @GET("movie/upcoming")
    fun loadUpComingMoviesService(): Single<Response<ListOfMovies>>

    @GET("{poster_path}")
    @Streaming
    fun loadPosterPictureService(
        @Path("poster_path")
        posterPath: String
    ): Single<Response<ResponseBody>>
}