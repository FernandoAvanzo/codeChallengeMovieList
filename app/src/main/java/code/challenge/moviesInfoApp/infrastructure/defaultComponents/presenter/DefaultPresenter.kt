package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.listOfMovies.view.activitys.AppMainActivity

abstract class DefaultPresenter(val view: DefaultView) {

    var flowErrorControl = -1

    val context by lazy { view.viewContext() }

    init {
        flowErrorControl = -1
    }

    abstract fun customSuccesBehavior(result: Any?, request: Any? = Any())

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

    fun attachNavigationFragment(fragment: Fragment){
        when(val activity = context){
            is AppMainActivity ->{
                activity.onAttachChildFragment(fragment)
            }
        }
    }

    private fun customLoaderBehavior(isLoading: Boolean) {
        when (isLoading) {
            true -> view.showLoading()
            else -> view.hideLoading()
        }
    }

    private fun customErrorBehavior(comunication: ComunicationProtocolModel) {
         view.onFailure(comunication)
         view.onError(comunication)
    }

}
