package code.challenge.moviesInfoApp.infrastructure.extensions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import code.challenge.moviesInfoApp.infrastructure.defaultComponents.views.DefaultView

fun View.onAttachFragment(child: Fragment) {
    val activity = this.context as? AppCompatActivity
    activity
        ?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(this.id, child)
        ?.commitNow()
}

fun emptyDefaultView() = object : DefaultView {}