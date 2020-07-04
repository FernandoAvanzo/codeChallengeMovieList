package code.challenge.movieList.infrastructure.defaultComponents.views

import android.content.Context
import code.challenge.movieList.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel

interface BaseView {
    fun showLoading() {}
    fun hideLoading() {}
    fun onFailure(erro: ComunicationProtocolModel) {}
    fun onAuthFailure(erro: ComunicationProtocolModel) {}
    fun onError(erro: ComunicationProtocolModel) {}
    fun onServerError(erro: ComunicationProtocolModel) {}
    fun showToolbar(show: Boolean){}
    fun showToolbarIconDownload(show: Boolean){}
    fun viewContext(): Context

}