package code.challenge.moviesInfoApp.listOfMovies.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ListOfMovies(
    @SerializedName("results")
    val results: ArrayList<Movie> = ArrayList(),
    @SerializedName("page")
    val page: Int = -1,
    @SerializedName("total_results")
    val totalResults: Int = 0,
    @SerializedName("dates")
    val dates: MovieDates = MovieDates(),
    @SerializedName("total_pages")
    val totalPages: Int = 0
) : Serializable