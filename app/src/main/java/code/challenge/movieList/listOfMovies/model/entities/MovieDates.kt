package code.challenge.movieList.listOfMovies.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDates(
    @SerializedName("maximum")
    val maximum: String = "",
    @SerializedName("minimum")
    val minimum: String = ""
) : Serializable