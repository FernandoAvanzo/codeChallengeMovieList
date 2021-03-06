package code.challenge.moviesInfoApp.listOfMovies.view.interfaces

import android.graphics.drawable.Drawable
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces.DefaultView

interface ActionMoviePoster: DefaultView {

    fun updatePoster(poster: Drawable){}
}