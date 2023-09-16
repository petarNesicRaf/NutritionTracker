package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.databinding.LayoutItemCategoryBinding
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.ContainerFragment
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import timber.log.Timber

class MealViewHolder(
    private val itemBinding: LayoutItemMealBinding,
    private val mycontext: Context,
    private  val fragContainer:FragmentFilterContainer? = null,
    private  val catContainer: CategoryFragmentCollection? = null
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity) {
        itemBinding.mealName.text = meal.strMeal

        //slika
        Glide.with(mycontext)
            .load(meal.strMealThumb)
            .into(itemBinding.image);

        itemBinding.mealLayout.setOnClickListener({
            Timber.e("lelele " + meal.strMeal +" " + meal.idMeal)
            if(fragContainer!= null && catContainer==null)
            {
                fragContainer!!.switchToDetailsFragment(meal.idMeal)
            }else{
                catContainer!!.switchToDetailsFragment(meal.idMeal)
            }
        })
    }


}