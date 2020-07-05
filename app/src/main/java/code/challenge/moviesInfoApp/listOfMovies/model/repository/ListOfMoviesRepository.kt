package code.challenge.moviesInfoApp.listOfMovies.model.repository

import code.challenge.moviesInfoApp.infrastructure.extensions.buildMoviePictureUrl
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.model.repository.provider.ListOfMoviesProvider
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter

class ListOfMoviesRepository(private val presenter: ListOfMoviesPresenter) {

    val movies by lazy { ArrayList<Movie>() }

    private val provider by lazy {
        ListOfMoviesProvider(
            presenter.view.viewContext(),
            presenter::defaultLoader
        )
    }

    private val posterProvider by lazy {
        ListOfMoviesProvider(
            presenter.view.viewContext(),
            presenter::defaultLoader,
            buildMoviePictureUrl()
        )
    }

    fun loadUpComingMovies() = provider.loadUpComingMovies()
    fun loadPosterPicture(movie: Movie) = posterProvider.loadPosterPicture(movie.posterPath)

    fun updatePage(page: ListOfMovies) {
        page.results.indices.map {
            movies.add(page.results[it])
            presenter.refreshInsertItem(it)
        }
        presenter.updateMovieList()
    }
}