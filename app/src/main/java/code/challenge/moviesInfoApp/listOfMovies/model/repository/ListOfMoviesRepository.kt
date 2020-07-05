package code.challenge.moviesInfoApp.listOfMovies.model.repository

import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.model.repository.provider.ListOfMoviesProvider
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter

class ListOfMoviesRepository(private val presenter: ListOfMoviesPresenter) {

    val movies: ArrayList<Movie> = ArrayList()

    private val provider by lazy {
        ListOfMoviesProvider(
            presenter.view.viewContext(),
            presenter::defaultLoader
        )
    }

    fun loadUpComingMovies() = provider.loadUpComingMovies()

    fun updatePage(page: ListOfMovies) {
        page.results.indices.map {
            movies.add(page.results[it])
            presenter.refreshInsertItem(it)
        }
        presenter.updateMovieList()
    }
}