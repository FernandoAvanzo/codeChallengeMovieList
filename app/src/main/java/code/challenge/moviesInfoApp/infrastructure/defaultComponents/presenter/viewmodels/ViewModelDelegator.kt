package code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import code.challenge.moviesInfoApp.MovieInfoApp
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.presenter.PresenterDelegator
import code.challenge.moviesInfoApp.infrastructure.extensions.buildBaseUrl
import code.challenge.moviesInfoApp.infrastructure.extensions.emptyDefaultPresenter

object ViewModelDelegator : ViewModel() {

    private val delegatorLiveData by lazy {
        MutableLiveData<PresenterDelegator>()
    }

    private val serviceHostLiveData by lazy {
        MutableLiveData(buildBaseUrl())
    }

    fun currentDelegator(delegator: PresenterDelegator) {
        delegatorLiveData.value = delegator
    }

    fun serviceHost(host: String) {
        serviceHostLiveData.value = host
    }

    fun getServiceHost(): String =
        serviceHostLiveData.value ?: buildBaseUrl()

    fun getDefaultLoader(): (ComunicationProtocolModel) -> Any =
        delegatorLiveData.value?.let {
            it::defaultLoader
        } ?: {}

    fun getCurrentContext(): Context =
        delegatorLiveData.value?.baseContext() ?: MovieInfoApp.APP

    fun getCurrentDelegator() = delegatorLiveData.value ?: emptyDefaultPresenter()

}