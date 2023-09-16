package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.databinding.LayoutItemAreaBinding
import rs.raf.vezbe11.databinding.LayoutItemIngredientBinding
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.recycler.diff.AreaDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.diff.IngredientDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.AreaViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.IngredientViewHolder

class IngredientAdapter(
    private val mycontext: Context,
    private val fragManager: FragmentManager,
    private val fragmentContainer: FragmentFilterContainer
): ListAdapter<IngredientEntity, IngredientViewHolder>(IngredientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        //
        val itemBinding = LayoutItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(itemBinding, mycontext, fragManager, fragmentContainer)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}