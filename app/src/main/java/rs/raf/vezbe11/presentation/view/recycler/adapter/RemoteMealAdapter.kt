package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemRemoteMealBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteContainer
import rs.raf.vezbe11.presentation.view.fragments.RecycleMealFragment
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.RemoteMealViewHolder
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel

class RemoteMealAdapter(
    private val mycontext: Context,
    private val remoteContainer:PlanRemoteContainer,
    private val planViewModel: PlanViewModel,
    private val vl: LifecycleOwner,
    private val dayString:String,
    private val planId:String,
    private val r: RecycleMealFragment
) : ListAdapter<MealEntity, RemoteMealViewHolder>(MealDiffCallback()), Filterable {

    private var list = listOf<MealEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteMealViewHolder {
        val itemBinding = LayoutItemRemoteMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RemoteMealViewHolder(itemBinding, mycontext, remoteContainer,planViewModel,vl,dayString,planId,r)
        // return MealViewHolder(itemBinding, mycontext, containerFragmentFilterContainer)

    }

    override fun onBindViewHolder(holder: RemoteMealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: List<MealEntity>) {
        this.list = list
        submitList(list)
    }

    override fun getFilter(): android.widget.Filter {
        return customFilter
    }

    private val customFilter = object : android.widget.Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<MealEntity>()
            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(list)
            } else {
                for (item in list) {
                    if (item.strMeal.toLowerCase()
                            .startsWith(constraint.toString().toLowerCase())
                    ) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, filterResults: FilterResults?) {
            submitList(filterResults?.values as MutableList<MealEntity>)
        }
    }
}
