package code.challenge.moviesInfoApp.infrastructure.extensions

import android.view.View
import androidx.fragment.app.Fragment

fun Fragment.buildChildFragment(container: View, child:Fragment){
    this.parentFragmentManager
        .beginTransaction()
        .replace(container.id,child)
        .commit()
}

