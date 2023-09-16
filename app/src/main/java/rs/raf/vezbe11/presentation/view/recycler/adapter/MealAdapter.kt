package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.recycler.diff.MealDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.diff.MovieDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.CategoryViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MealViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MovieViewHolder

class MealAdapter(
    private val mycontext: Context,
    //private val containerFragmentFilterContainer: FragmentFilterContainer
    private  val fragContainer:FragmentFilterContainer? ,
    private  val catContainer: CategoryFragmentCollection?
) : ListAdapter<MealEntity, MealViewHolder>(MealDiffCallback()), Filterable {

    private var list = listOf<MealEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemBinding = LayoutItemMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if(fragContainer == null && catContainer!=null) {
            return MealViewHolder(itemBinding, mycontext, null,catContainer!!)
        } else (fragContainer!=null && catContainer==null)
        return MealViewHolder(  itemBinding, mycontext, fragContainer!!, null)
       // return MealViewHolder(itemBinding, mycontext, containerFragmentFilterContainer)

    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(list: List<MealEntity>){
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
                    if (item.strMeal.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
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