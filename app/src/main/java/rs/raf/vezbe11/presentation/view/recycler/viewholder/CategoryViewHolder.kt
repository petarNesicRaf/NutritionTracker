package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemCategoryBinding
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding
import rs.raf.vezbe11.presentation.view.activities.LandingActivity
import rs.raf.vezbe11.presentation.view.adapters.CategoryPagerAdapter
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteContainer
import rs.raf.vezbe11.presentation.view.fragments.RecycleFragment
import rs.raf.vezbe11.presentation.view.fragments.RecycleMealFragment

class CategoryViewHolder(
    private val itemBinding: LayoutItemCategoryBinding,
    private val mycontext: Context,
    private val fragmentManager: FragmentManager,
    //private val fragContainer: FragmentFilterContainer
    private  val fragContainer:FragmentFilterContainer? ,
    private  val catContainer:CategoryFragmentCollection?,
    private val remoteContainer: PlanRemoteContainer?
) : RecyclerView.ViewHolder(itemBinding.root) {


    fun bind(category: CategoryEntity) {
        itemBinding.categoryName.text = category.strCategory
        itemBinding.itemLayout.setOnClickListener { view ->
            when (view.id) {
                R.id.btnDescription -> {
                   showDescriptionDialog(category.strCategoryDescription,category.strCategory)
                }
                else -> {
                    //menjaj filter
                    if(fragContainer != null && catContainer==null){
                        fragContainer!!.switchToCategoryFragment(category.strCategory)
                    }
                    if(fragContainer==null && catContainer!=null){
                        catContainer!!.switchToMealFragment(category.strCategory)
                    }
                    if(fragContainer == null && catContainer==null && remoteContainer!=null)
                    {
                        remoteContainer!!.switchToMealRecycler(category.strCategory)
                    }
                }
            }
        }
        //slika
        Glide.with( mycontext)
            .load(category.strCategoryThumb)
            .into(itemBinding.image);
    }

    private fun showDescriptionDialog(description: String, name: String) {
        val dialogBuilder = AlertDialog.Builder(itemBinding.root.context)
        dialogBuilder.setTitle(name)
        dialogBuilder.setMessage(description)
        dialogBuilder.setPositiveButton("Close", null)
        val dialog = dialogBuilder.create()
        dialog.show()
    }
}