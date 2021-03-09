package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views

import android.content.Context
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.MovieInfoApp
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel

interface DefaultView {
    fun showLoading() {}
    fun hideLoading() {}
    fun onFailure(erro: ComunicationProtocolModel) {}
    fun onAuthFailure(erro: ComunicationProtocolModel) {}
    fun onError(erro: ComunicationProtocolModel) {}
    fun onServerError(erro: ComunicationProtocolModel) {}
    fun showToolbar(show: Boolean){}
    fun showToolbarIconDownload(show: Boolean){}
    fun viewContext():Context = MovieInfoApp.APP
    fun updateListView() {}
    fun updateInsertedList(id: Int) {}
    fun onAttachChildFragment(child: Fragment){}

}