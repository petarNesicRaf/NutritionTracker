package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import androidx.viewpager.widget.PagerAdapter
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemCategoryBinding
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteContainer
import rs.raf.vezbe11.presentation.view.recycler.diff.CategoryDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.diff.MovieDiffCallback
import rs.raf.vezbe11.presentation.view.recycler.viewholder.CategoryViewHolder
import rs.raf.vezbe11.presentation.view.recycler.viewholder.MovieViewHolder

class CategoryAdapter(
    private val  mycontext: Context,
    private val  fragManager:FragmentManager,
    private val fragContainer:FragmentFilterContainer?,
    private val catContainer:CategoryFragmentCollection?,
    private val remoteContainer: PlanRemoteContainer?
): ListAdapter<CategoryEntity, CategoryViewHolder>(CategoryDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        //
        val itemBinding =
            LayoutItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        if (fragContainer == null && catContainer != null) {
            return CategoryViewHolder(itemBinding, mycontext, fragManager, null, catContainer!!, null)
        } else if(fragContainer != null && catContainer == null){
            return CategoryViewHolder(itemBinding, mycontext, fragManager, fragContainer!!, null, null)
        }else{
            return CategoryViewHolder(itemBinding, mycontext, fragManager, null, null, remoteContainer!!)
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}