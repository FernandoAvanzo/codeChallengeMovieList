package code.challenge.moviesInfoApp.infrastructure.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Int.buildViewBinding(inflater: LayoutInflater, container: ViewGroup?): T {
    return inflater.defaultMakeBinding(container, this)
}

fun Int.getDrawable(context: Context): Drawable {
    return context.resources.getDrawable(this, context.theme)
}
