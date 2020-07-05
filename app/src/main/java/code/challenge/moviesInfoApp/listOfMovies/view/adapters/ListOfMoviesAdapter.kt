package code.challenge.moviesInfoApp.listOfMovies.view.adapters

import android.content.Context
import code.challenge.moviesInfoApp.R.layout.item_movie_list
import code.challenge.moviesInfoApp.databinding.ItemMovieListBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.adapter.DefaultAdapter
import code.challenge.moviesInfoApp.infrastructure.extensions.onAttachFragment
import code.challenge.moviesInfoApp.listOfMovies.model.entities.Movie
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter

class ListOfMoviesAdapter(context: Context) : DefaultAdapter<ItemMovieListBinding>(context){

    private val presenter by lazy { ListOfMoviesPresenter(this) }

    init {
        presenter.loadUpComingMovies()
    }

    override fun idLayout() = item_movie_list
    override fun viewContext() = baseContext
    override fun getItemCount() = presenter.movieListSize()
    override fun updateListView() = notifyDataSetChanged()
    override fun updateInsertedList(id: Int) = notifyItemInserted(id)

    override fun onBindViewHolder(holder: DefaultHolder, position: Int) {
        holder.item.model = presenter.takeMove(position)
        updateMovieThumbnail(holder)
    }

    private fun buildFragmentThumbnail(model: Movie) = presenter.buildPosterThumbnail(model)

    private fun updateMovieThumbnail(holder: DefaultHolder) {
        val item = holder.item
        val model = item.model?:Movie()
        item.moviePosterIcon.onAttachFragment(buildFragmentThumbnail(model))
    }
}