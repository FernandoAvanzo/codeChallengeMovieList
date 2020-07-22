package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.listOfMovies.view.activitys.AppMainActivity

abstract class DefaultPresenter(val view: DefaultView) {

    val context by lazy { view.viewContext() }

    abstract fun customSuccesBehavior(result: Any?, request: Any? = Any())
    abstract fun customErrorBehavior(comunication: ComunicationProtocolModel)
    abstract fun customLoaderBehavior(isLoading: Boolean)

    fun defaultLoader(comunication: ComunicationProtocolModel) {
        comunication.load?.let {
            when (it) {
                true -> customLoaderBehavior(it)
                else -> {
                    customLoaderBehavior(it)
                    when (comunication.isError) {
                        true -> customErrorBehavior(comunication)
                        else -> customSuccesBehavior(comunication.result, comunication.request)
                    }
                }
            }
        }
    }

    fun defaultErrorBehavior(comunication: ComunicationProtocolModel) {
        view.onFailure(comunication)
        view.onError(comunication)
    }

    fun defaultLoaderBehavior(isLoading: Boolean) {
        when (isLoading) {
            true -> view.showLoading()
            else -> view.hideLoading()
        }
    }

    fun attachNavigationFragment(fragment: Fragment){
        when(val activity = context){
            is AppMainActivity ->{
                activity.onAttachChildFragment(fragment)
            }
        }
    }

}
