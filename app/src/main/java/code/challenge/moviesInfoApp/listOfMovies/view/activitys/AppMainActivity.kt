package code.challenge.moviesInfoApp.listOfMovies.view.activitys

import android.os.Bundle
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.R.layout.main_activity
import code.challenge.moviesInfoApp.databinding.MainActivityBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.activity.DefaultActivity
import code.challenge.moviesInfoApp.infrastructure.extensions.onAttachFragment
import code.challenge.moviesInfoApp.listOfMovies.presenter.ListOfMoviesPresenter

class AppMainActivity : DefaultActivity<MainActivityBinding>() {

    private val presenter by lazy { ListOfMoviesPresenter(this) }

    override fun activityLayout() = main_activity

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        presenter.buildPosterListOfMovie()
    }

    override fun onAttachChildFragment(child: Fragment) {
        activityBinding.mainContainerFragment.onAttachFragment(child)
    }

}