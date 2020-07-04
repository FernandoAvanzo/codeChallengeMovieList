package code.challenge.movieList.infrastructure.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Int.buildViewBinding(inflater: LayoutInflater, container: ViewGroup?): T {
    return inflater.defaultMakeBinding(container, this)
}
