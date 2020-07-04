package code.challenge.movieList.infrastructure.defaultComponents.views.adapter

import android.content.Context
import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import code.challenge.movieList.infrastructure.extensions.buildViewBinding

abstract class DefaultAdapter<T : ViewDataBinding>(val baseContext: Context)
    : Adapter<DefaultAdapter<T>.DefaultHolder>() {

    inner class DefaultHolder(val item: T) : ViewHolder(item.root)

    abstract fun idLayout(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DefaultHolder(makeBinding(parent))

    private fun makeBinding(container: ViewGroup): T {
        return idLayout().buildViewBinding(from(baseContext), container)
    }
}
