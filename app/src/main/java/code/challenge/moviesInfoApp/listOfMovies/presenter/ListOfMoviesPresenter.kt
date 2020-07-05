package code.challenge.moviesInfoApp.listOfMovies.presenter

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.DefaultPresenter
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.model.repository.ListOfMoviesRepository

class ListOfMoviesPresenter(moviesView: DefaultView): DefaultPresenter(moviesView) {

    private val repository by lazy { ListOfMoviesRepository(this) }

    override fun customSuccesBehavior(result: Any?) {
        result?.let {
            when (it) {
                is ListOfMovies -> repository.updatePage(it)
            }
        }
    }

    fun movieListSize() = repository.movies.size
    fun loadUpComingMovies() = repository.loadUpComingMovies()
    fun updateMovieList() = view.updateListView()
    fun refreshInsertItem(id: Int) = view.updateInsertedList(id)
    fun takeMove(id: Int) = movieList()
        .takeIf { id in 0 until it.size }?.get(id) ?: Movie()

    private fun movieList() = repository.movies
}