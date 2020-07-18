package code.challenge.moviesInfoApp.listOfMovies.view.fragments

import android.os.Bundle
import android.view.View
import code.challenge.moviesInfoApp.R.layout.fragment_movie_list
import code.challenge.moviesInfoApp.databinding.FragmentMovieListBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.fragment.DefaultFragment
import code.challenge.moviesInfoApp.listOfMovies.view.adapters.ListOfMoviesAdapter
import code.challenge.moviesInfoApp.listOfMovies.view.decorations.ListOfMoviesDecoration

class FragmentMovieList :DefaultFragment<FragmentMovieListBinding>() {

    private val adapter by lazy { ListOfMoviesAdapter(requireContext()) }

    override fun fragmentLayout() = fragment_movie_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        defaultBinding.controller = this
    }

    fun buildAdapter(): ListOfMoviesAdapter {
        defaultBinding.movieListRecycler
            .addItemDecoration(ListOfMoviesDecoration())
        return adapter
    }

}