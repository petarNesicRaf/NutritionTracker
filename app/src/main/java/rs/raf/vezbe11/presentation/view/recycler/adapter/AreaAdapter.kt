package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.databinding.LayoutItemAreaBinding
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.recycler.diff.AreaDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.AreaViewHolder

class AreaAdapter(
    private val mycontext: Context,
    private val fragManager: FragmentManager,
    private val fragmentContainer: FragmentFilterContainer
    ): ListAdapter<AreaEntity, AreaViewHolder>(AreaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
            //
            val itemBinding = LayoutItemAreaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return AreaViewHolder(itemBinding, mycontext, fragManager, fragmentContainer)
        }

    override fun onBindViewHolder(holder: AreaViewHolder, position: Int) {
            holder.bind(getItem(position))
    }
}