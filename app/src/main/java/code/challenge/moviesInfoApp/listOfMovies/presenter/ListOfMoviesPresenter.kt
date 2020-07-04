package code.challenge.moviesInfoApp.listOfMovies.presenter

import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.DefaultPresenter
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView

class ListOfMoviesPresenter(private val moviesView: DefaultView): DefaultPresenter(moviesView) {

    override fun customSuccesBehavior(result: Any?) {
        result?.let {  }
    }
}