package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.model.entities.ToolbarSettings
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces.DefaultView
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.interfaces.Navigation
import code.challenge.moviesInfoApp.infrastructure.extensions.buildViewBinding
import code.challenge.moviesInfoApp.infrastructure.extensions.emptyDefaultView
import code.challenge.moviesInfoApp.infrastructure.extensions.getString
import java.util.*

abstract class DefaultFragment<T : ViewDataBinding> :  DefaultView, Navigation, Fragment() {

    lateinit var defaultBinding: T

    private val rootScreen by lazy { requireActivity() as? DefaultView }

    abstract fun fragmentLayout(): Int

    override fun viewContext(): Context = requireActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = makeBinding(inflater, container).root

    fun isInicializadBinding() = this::defaultBinding.isInitialized

    fun loadRootScreen(): DefaultView = rootScreen ?: emptyDefaultView()

    fun buildToolbarTitle(id: Int) = id
        .getString(requireContext())
        .toUpperCase(Locale.ROOT)

    fun loadToolbarSettings() {
        val toolbar = buildToolbarSettings()
        loadRootScreen().applyToolbarTitle(toolbar.toolBarTitle)
        loadRootScreen().showToolbar(toolbar.showToolBar)
        configBackFeature(toolbar)
        configCloseFeature(toolbar)
        configRightCloseFeature(toolbar)
        configShareFeature(toolbar)
        configHelpFeature(toolbar)
    }

    private fun configBackFeature(settings: ToolbarSettings) {
        loadRootScreen().showToolbarIconBack(settings.hasBackAction)
        when(settings.hasBackAction){
            true->loadRootScreen().applyArrowBackAction(settings)
            else->loadRootScreen().applyArrowBackAction(ToolbarSettings())
        }
    }

    private fun configShareFeature(settings: ToolbarSettings) {
        loadRootScreen().showToolbarIconShare(settings.hasShareAction)
        settings.takeIf { it.hasShareAction }?.let {
            loadRootScreen().applyShareAction(settings)
        }
    }

    private fun configCloseFeature(settings: ToolbarSettings){
        loadRootScreen().showToolbarIconClose(settings.hasCloseAction)
        settings.takeIf { it.hasCloseAction }?.let {
            loadRootScreen().showToolbarIconBack(false)
            loadRootScreen().applyArrowBackAction(settings)
            loadRootScreen().applyCloseAction(settings)
        }
    }

    private fun configRightCloseFeature(settings: ToolbarSettings){
        loadRootScreen().showToolbarRightIconClose(settings.hasCloseRightAction)
        settings.takeIf { it.hasCloseRightAction }?.let {
            loadRootScreen().applyRightCloseAction(settings)
        }
    }

    private fun configHelpFeature(settings: ToolbarSettings){
        loadRootScreen().showToolbarIconHelp(settings.hasHelpAction)
        settings.takeIf { it.hasHelpAction }?.let {
            loadRootScreen().applyHelpAction(settings)
        }
    }

    private fun makeBinding(inflater: LayoutInflater, container: ViewGroup?): T {
        defaultBinding = fragmentLayout().buildViewBinding(inflater, container)
        return defaultBinding
    }
}
