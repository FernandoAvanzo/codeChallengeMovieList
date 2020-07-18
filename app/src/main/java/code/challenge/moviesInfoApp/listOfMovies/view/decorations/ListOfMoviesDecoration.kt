package code.challenge.moviesInfoApp.listOfMovies.view.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class ListOfMoviesDecoration : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val pagination = parent.adapter as? PaginationAction
        pagination?.let { pageControl ->
            val position = parent.getChildAdapterPosition(view)
            takeIf { pageControl.paginationSpace(position) }?.let {
                pageControl.loadNextPage()
            }
        }
    }

    interface PaginationAction {

        fun paginationSpace(position: Int): Boolean = false

        fun loadNextPage() {}
    }
}