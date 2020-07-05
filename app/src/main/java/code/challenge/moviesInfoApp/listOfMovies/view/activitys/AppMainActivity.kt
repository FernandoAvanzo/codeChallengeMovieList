package code.challenge.moviesInfoApp.listOfMovies.view.activitys

import android.os.Bundle
import code.challenge.moviesInfoApp.R.layout.main_activity
import code.challenge.moviesInfoApp.databinding.MainActivityBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.activity.DefaultActivity
import code.challenge.moviesInfoApp.infrastructure.extensions.onAttachFragment
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMovieList

class AppMainActivity : DefaultActivity<MainActivityBinding>() {
    override fun activityLayout() = main_activity

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        activityBinding.mainContainerFragment.onAttachFragment(FragmentMovieList())
    }

}