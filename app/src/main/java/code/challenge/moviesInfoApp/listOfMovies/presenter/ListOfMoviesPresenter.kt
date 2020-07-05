package code.challenge.moviesInfoApp.listOfMovies.presenter

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.DefaultPresenter
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.infrastructure.extensions.buildBitampFromStream
import code.challenge.moviesInfoApp.infrastructure.extensions.buildDrawableAsset
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.model.repository.ListOfMoviesRepository
import code.challenge.moviesInfoApp.listOfMovies.view.activitys.AppMainActivity
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMovieList
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMoviePoster
import code.challenge.moviesInfoApp.listOfMovies.view.interfaces.ActionMoviePoster
import okhttp3.ResponseBody
import kotlin.math.atan

class ListOfMoviesPresenter(moviesView: DefaultView): DefaultPresenter(moviesView) {

    private val repository by lazy { ListOfMoviesRepository(this) }
    private val actionPoster by lazy { view as? ActionMoviePoster }

    override fun customSuccesBehavior(result: Any?) {
        result?.let {
            when (it) {
                is ListOfMovies -> repository.updatePage(it)
                is ResponseBody -> updatePoster(it)
            }
        }
    }

    fun movieListSize() = repository.movies.size
    fun loadUpComingMovies() = repository.loadUpComingMovies()
    fun loadPosterPicture(movie: Movie) = repository.loadPosterPicture(movie)

    fun updateMovieList() = view.updateListView()
    fun refreshInsertItem(id: Int) = view.updateInsertedList(id)
    fun takeMove(id: Int) = movieList()
        .takeIf { id in 0 until it.size }?.get(id) ?: Movie()

    fun buildPosterThumbnail(movie: Movie){
        when(val activity = context){
            is AppMainActivity->{
                activity.onAttachChildFragment(FragmentMoviePoster(movie))
            }
        }
    }

    fun buildPosterListOfMovie(){
        when(val activity = context){
            is AppMainActivity->{
                activity.onAttachChildFragment(FragmentMovieList())
            }
        }
    }

    private fun movieList() = repository.movies

    private fun updatePoster(model: ResponseBody){
        actionPoster?.let {
            val posterBitmap = buildBitampFromStream(model.byteStream())
            it.updatePoster(context.buildDrawableAsset(posterBitmap))
        }
    }
}