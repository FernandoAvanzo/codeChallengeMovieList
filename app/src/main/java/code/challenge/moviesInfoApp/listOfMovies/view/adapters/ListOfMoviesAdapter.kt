package code.challenge.moviesInfoApp.listOfMovies.view.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import code.challenge.moviesInfoApp.R.drawable.ic_launcher_foreground
import code.challenge.moviesInfoApp.R.layout.item_movie_list
import code.challenge.moviesInfoApp.databinding.ItemMovieListBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.adapter.DefaultAdapter
import code.challenge.moviesInfoApp.infrastructure.extensions.buildDrawableAsset
import code.challenge.moviesInfoApp.infrastructure.extensions.getDrawable
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter
import code.challenge.moviesInfoApp.listOfMovies.view.decorations.ListOfMoviesDecoration.PaginationAction
import kotlin.math.absoluteValue

class ListOfMoviesAdapter(context: Context) :
    DefaultAdapter<ItemMovieListBinding>(context), PaginationAction {

    private val presenter by lazy { ListOfMoviesPresenter(this) }

    init {
        presenter.loadUpComingMovies()
    }

    override fun idLayout() = item_movie_list
    override fun viewContext(): Context = baseContext
    override fun getItemCount() = presenter.movieListSize()
    override fun updateListView() = notifyDataSetChanged()
    override fun updateInsertedList(id: Int) = notifyItemInserted(id)
    override fun paginationSpace(position: Int) = checkPaginationSpace(position)

    override fun onBindViewHolder(holder: DefaultHolder, position: Int) {
        holder.item.controller = this
        holder.item.model = presenter.takeMove(position)
    }

    override fun loadNextPage() {
        when (hasNextPage()) {
            true -> presenter.loadUpComingMovies(nextPage())
        }
    }

    fun movieDetailsAction(model: Movie) = View.OnClickListener {
        presenter.buildPosterThumbnail(model)
    }

    fun movieThumbnail(model: Movie): Drawable {
        return presenter.getThumbnail(model)?.let {
            baseContext.buildDrawableAsset(it)
        } ?: ic_launcher_foreground.getDrawable(baseContext)
    }

    private fun nextPage() = presenter.nextPage()
    private fun hasNextPage() = presenter.hasNextPage()
    private fun checkPaginationSpace(position: Int) =
        position in (itemCount - 15).absoluteValue until itemCount
}