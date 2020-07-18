package code.challenge.moviesInfoApp.listOfMovies.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ThumbnailRequest(
    @SerializedName("movieId")
    val movieId: Int = -1,
    @SerializedName("movie")
    val movie: Movie = Movie()
) : Serializable