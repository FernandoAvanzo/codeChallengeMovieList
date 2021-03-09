package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces

import android.content.Context
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.MovieInfoApp
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ComunicationProtocolModel
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ToolbarSettings

interface DefaultView {
    fun showLoading() {}
    fun hideLoading() {}
    fun onFailure(erro: ComunicationProtocolModel) {}
    fun onAuthFailure(erro: ComunicationProtocolModel) {}
    fun onError(erro: ComunicationProtocolModel) {}
    fun onServerError(erro: ComunicationProtocolModel) {}
    fun showToolbar(show: Boolean){}
    fun showToolbarIconDownload(show: Boolean){}
    fun showToolbarIconBack(show: Boolean) {}
    fun showToolbarIconShare(show: Boolean) {}
    fun showToolbarIconClose(show: Boolean) {}
    fun showToolbarRightIconClose(show: Boolean) {}
    fun showToolbarIconHelp(show: Boolean) {}
    fun applyToolbarTitle(title: String) {}
    fun applyArrowBackAction(settings: ToolbarSettings) {}
    fun applyShareAction(settings: ToolbarSettings) {}
    fun applyCloseAction(settings: ToolbarSettings) {}
    fun applyRightCloseAction(settings: ToolbarSettings) {}
    fun applyHelpAction(settings: ToolbarSettings) {}
    fun viewContext():Context = MovieInfoApp.APP
    fun updateListView() {}
    fun updateInsertedList(id: Int) {}
    fun onAttachChildFragment(child: Fragment){}

}