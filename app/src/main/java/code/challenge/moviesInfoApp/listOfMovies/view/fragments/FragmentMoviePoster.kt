package code.challenge.moviesInfoApp.listOfMovies.view.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import code.challenge.moviesInfoApp.R.layout.fragment_movie_poster
import code.challenge.moviesInfoApp.databinding.FragmentMoviePosterBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.fragment.DefaultFragment
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter
import code.challenge.moviesInfoApp.listOfMovies.view.interfaces.ActionMoviePoster

class FragmentMoviePoster(private val movie: Movie = Movie()) :
    DefaultFragment<FragmentMoviePosterBinding>(), ActionMoviePoster {

    private val presenter by lazy { ListOfMoviesPresenter(this) }

    override fun fragmentLayout() = fragment_movie_poster

    override fun onResume() {
        super.onResume()
        presenter.loadPosterPicture(movie)
    }

    override fun updatePoster(poster: Drawable) {
        defaultBinding
            .moviePosterThumbnail
            .setImageDrawable(poster)
    }
}