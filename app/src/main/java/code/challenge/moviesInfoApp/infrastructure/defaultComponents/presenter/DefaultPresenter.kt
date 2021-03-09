package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter

import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.RequestOrigin
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces.DefaultView
import code.challenge.moviesInfoApp.infrastructure.extensions.buildBaseUrl
import code.challenge.moviesInfoApp.listOfMovies.view.activitys.AppMainActivity

abstract class DefaultPresenter(val view: DefaultView): PresenterDelegator {

    private var origin = RequestOrigin.DEFAULT

    val context by lazy { view.viewContext() }

    abstract fun customSuccesBehavior(result: Any?, request: Any? = Any())

    override fun customErrorBehavior(comunication: ComunicationProtocolModel){
        view.onFailure(comunication)
        view.onError(comunication)
    }

    override fun customLoaderBehavior(isLoading: Boolean){
        when (isLoading) {
            true -> view.showLoading()
            else -> view.hideLoading()
        }
    }

    override fun defaultLoader(comunication: ComunicationProtocolModel) {
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
        val activity = context as? AppMainActivity
        activity?.onAttachChildFragment(fragment)
    }

    fun setup(
        callOrigin: RequestOrigin = RequestOrigin.DEFAULT,
        host: String = buildBaseUrl()
    ) {
        origin = callOrigin
        registerServiceHost(host)
        registerCurrentDelegator(this)
    }
}
