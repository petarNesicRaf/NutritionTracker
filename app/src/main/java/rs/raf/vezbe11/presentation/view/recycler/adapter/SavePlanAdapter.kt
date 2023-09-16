package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import rs.raf.vezbe11.databinding.LayoutItemPlanMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSaveBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalFragment
import rs.raf.vezbe11.presentation.view.fragments.RecycleSaveFragment
import rs.raf.vezbe11.presentation.view.recycler.diff.SaveDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.SavePlanViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.SaveViewHolder
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel

class SavePlanAdapter (
    private val mycontext: Context,
    private val planLocalFragment: PlanLocalContainer,
    private val planViewModel: PlanViewModel,
    private val vl:LifecycleOwner,
    private val dayString:String,
    private val planId:String,
    private val r:RecycleSaveFragment,
) : ListAdapter<SaveEntity, SavePlanViewHolder>(SaveDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavePlanViewHolder {
        val itemBinding = LayoutItemPlanMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavePlanViewHolder(itemBinding, mycontext,planLocalFragment ,planViewModel,vl,dayString, planId, r,planLocalFragment.getItemv())

    }

    override fun onBindViewHolder(holder: SavePlanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
