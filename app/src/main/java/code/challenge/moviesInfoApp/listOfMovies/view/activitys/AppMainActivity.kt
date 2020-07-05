package code.challenge.moviesInfoApp.listOfMovies.view.activitys

import android.os.Bundle
import android.os.PersistableBundle
import code.challenge.moviesInfoApp.R
import code.challenge.moviesInfoApp.databinding.MainActivityBinding
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.activity.DefaultActivity
import code.challenge.moviesInfoApp.infrastructure.extensions.onAttachFragment
import code.challenge.moviesInfoApp.listOfMovies.view.fragments.FragmentMovieList

class AppMainActivity : DefaultActivity<MainActivityBinding>() {
    override fun activityLayout() = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        activityBinding.mainContainerFragment.onAttachFragment(FragmentMovieList())
    }
}