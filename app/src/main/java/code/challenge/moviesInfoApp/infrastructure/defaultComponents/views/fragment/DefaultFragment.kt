package code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView
import code.challenge.moviesInfoApp.infrastructure.extensions.buildViewBinding

abstract class DefaultFragment<T : ViewDataBinding> : Fragment(), DefaultView {

    lateinit var defaultBinding: T

    abstract fun fragmentLayout(): Int

    override fun viewContext(): Context = requireActivity()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = makeBinding(inflater, container).root

    fun isInicializadBinding() = this::defaultBinding.isInitialized

    private fun makeBinding(inflater: LayoutInflater, container: ViewGroup?): T {
        defaultBinding = fragmentLayout().buildViewBinding(inflater, container)
        return defaultBinding
    }
}
