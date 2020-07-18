package code.challenge.moviesInfoApp.listOfMovies.presenter

import android.graphics.Bitmap
import code.challenge.moviesInfoApp.infrastructure.constants.ConstantsListOfMovies.POSTER_PICTURE
import code.challenge.moviesInfoApp.infrastructure.constants.ConstantsListOfMovies.THUMBNAIL_PICTURE
import code.challenge.moviesInfoApp.infrastructure.constants.ConstantsListOfMovies.UNKNOWN
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.DefaultPresenter
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.infrastructure.extensions.buildBitampFromStream
import code.challenge.moviesInfoApp.infrastructure.extensions.buildDrawableAsset
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ListOfMovies
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.model.entities.ThumbnailRequest
import code.challenge.moviesInfoApp.listOfMovies.model.repository.ListOfMoviesRepository
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMovieList
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMoviePoster
import code.challenge.moviesInfoApp.listOfMovies.view.interfaces.ActionMoviePoster
import okhttp3.ResponseBody

class ListOfMoviesPresenter(moviesView: DefaultView): DefaultPresenter(moviesView) {

    private var picturetype = UNKNOWN
    private var thumbnailRequest = ThumbnailRequest()

    private val repository by lazy { ListOfMoviesRepository(this) }
    private val actionPoster by lazy { view as? ActionMoviePoster }

    override fun customSuccesBehavior(result: Any?) {
        result?.let {
            when (it) {
                is ListOfMovies -> repository.updatePage(it)
                is ResponseBody -> pictureResponse(it)
            }
        }
    }

    fun movieListSize() = repository.movies.size
    fun loadUpComingMovies(page: Int = 1) = repository.loadUpComingMovies(page)

    fun hasNextPage() = repository.hasNextPage()
    fun nextPage() = repository.nextPage()
    fun updateMovieList() = view.updateListView()
    fun refreshInsertItem(id: Int) = view.updateInsertedList(id)
    fun takeMove(id: Int) = movieList()
        .takeIf { id in 0 until it.size }?.get(id) ?: Movie()

    fun loadPosterPicture(movie: Movie) {
        picturetype = POSTER_PICTURE
        repository.loadPosterPicture(movie)
    }

    fun loadThumbnailPicture(thumbnail: ThumbnailRequest) {
        picturetype = THUMBNAIL_PICTURE
        thumbnailRequest = thumbnail
        repository.loadPosterPicture(thumbnail.movie)
    }

    fun buildPosterThumbnail(movie: Movie) {
        attachNavigationFragment(FragmentMoviePoster(movie))
    }

    fun buildPosterListOfMovie() {
        attachNavigationFragment(FragmentMovieList())
    }

    private fun pictureResponse(model: ResponseBody) {
        when (picturetype) {
            THUMBNAIL_PICTURE -> buildMovieThumbnail(model)
            POSTER_PICTURE -> updatePoster(model)
        }
    }

    private fun buildMovieThumbnail(model: ResponseBody) {
        val thumbnailBitmap = buildBitampFromStream(model.byteStream())
        addMovieThumbnail(thumbnailBitmap)
        refreshMovieList()
    }

    private fun updatePoster(model: ResponseBody) {
        actionPoster?.let {
            val posterBitmap = buildBitampFromStream(model.byteStream())
            it.updatePoster(context.buildDrawableAsset(posterBitmap))
        }
    }

    private fun movieList() = repository.movies
    private fun refreshMovieList() = repository.refreshMovieList(thumbnailRequest)
    private fun addMovieThumbnail(thumbnail: Bitmap) =
        repository.addThumbnail(thumbnailRequest.movie, thumbnail)
}