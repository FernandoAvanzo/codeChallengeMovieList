package code.challenge.moviesInfoApp.listOfMovies.view.adapters

import android.content.Context
import code.challenge.moviesInfoApp.R.layout.item_movie_list
import code.challenge.moviesInfoApp.databinding.ItemMovieListBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.adapter.DefaultAdapter

class ListOfMoviesAdapter(context: Context) : DefaultAdapter<ItemMovieListBinding>(context),
    DefaultView {

    override fun idLayout() = item_movie_list
    override fun viewContext() = baseContext

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DefaultHolder, position: Int) {
        TODO("Not yet implemented")
    }
}