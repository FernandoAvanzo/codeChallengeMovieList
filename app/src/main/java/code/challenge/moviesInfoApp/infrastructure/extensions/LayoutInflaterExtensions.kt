package code.challenge.moviesInfoApp.infrastructure.extensions

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding


fun <T : ViewDataBinding> LayoutInflater.defaultMakeBinding(container: ViewGroup?,
                                                            viewId: Int,
                                                            attachToParent: Boolean = false): T {
    return inflate(this, viewId, container, attachToParent )
}