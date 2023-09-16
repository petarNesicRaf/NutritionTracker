package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSaveBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.recycler.diff.SaveDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.SaveViewHolder
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel

class SaveAdapter   (
    private val mycontext: Context,
    private  val fragContainer: FragmentFilterContainer?,
    private  val catContainer: CategoryFragmentCollection?,
    private val saveViewModel: SaveViewModel
) : ListAdapter<SaveEntity, SaveViewHolder>(SaveDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val itemBinding = LayoutItemSaveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if(fragContainer == null && catContainer!=null) {
            return SaveViewHolder(itemBinding, mycontext, null,catContainer!!,saveViewModel)
        } else (fragContainer!=null && catContainer==null)
        return SaveViewHolder(  itemBinding, mycontext, fragContainer!!, null, saveViewModel)

    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}