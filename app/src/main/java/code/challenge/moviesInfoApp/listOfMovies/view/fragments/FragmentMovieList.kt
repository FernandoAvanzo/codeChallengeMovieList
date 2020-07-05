package code.challenge.moviesInfoApp.listOfMovies.view.fragments

import android.content.Context
import code.challenge.moviesInfoApp.R.layout.fragment_movie_list
import code.challenge.moviesInfoApp.databinding.FragmentMovieListBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.fragment.DefaultFragment

class FragmentMovieList :DefaultFragment<FragmentMovieListBinding>() {

    override fun fragmentLayout() = fragment_movie_list

}