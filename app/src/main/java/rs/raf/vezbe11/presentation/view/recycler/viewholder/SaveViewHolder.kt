package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemSaveBinding
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.viewmodel.SaveViewModel
import timber.log.Timber

class SaveViewHolder(
    private val itemBinding: LayoutItemSaveBinding,
    private val mycontext: Context,
    private  val fragContainer: FragmentFilterContainer? = null,
    private  val catContainer: CategoryFragmentCollection? = null,
    private val saveViewHolder: SaveViewModel
) : RecyclerView.ViewHolder(itemBinding.root) {

    //todo dodaj observere
    fun bind(saveEntity: SaveEntity) {
        itemBinding.mealName.text = saveEntity.strMeal

        //slika
        Glide.with(mycontext)
            .load(saveEntity.strMealThumb)
            .into(itemBinding.image);

        itemBinding.mealLayout.setOnClickListener({
            if(fragContainer!= null && catContainer==null)
            {
                fragContainer!!.switchToDetailsFragment(saveEntity.idMeal!!.toInt())
            }else{
                catContainer!!.switchToDetailsFragment(saveEntity.idMeal!!.toInt())
            }
        })

        itemBinding.btnDelete.setOnClickListener({
            saveViewHolder.deleteSaved(saveEntity.idMeal!!)
        })

        itemBinding.btnEdit.setOnClickListener({
            if(fragContainer!= null && catContainer==null)
            {
                fragContainer!!.switchWithEditFragment(saveEntity)
            }else{
                catContainer!!.switchWithEditFragment(saveEntity)
            }
        })
    }


}