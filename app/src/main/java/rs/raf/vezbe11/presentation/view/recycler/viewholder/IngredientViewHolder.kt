package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.databinding.LayoutItemAreaBinding
import rs.raf.vezbe11.databinding.LayoutItemIngredientBinding
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer

class IngredientViewHolder (
    private val itemBinding: LayoutItemIngredientBinding,
    private val mycontext: Context,
    private val fragmentManager: FragmentManager,
    private val fragmentContatiner: FragmentFilterContainer
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(ingr: IngredientEntity) {
        itemBinding.tvIngr.text =ingr.strIngredient
        itemBinding.tvDescr.text =ingr.strDescription
        itemBinding.tvType.text =ingr.strType

        itemBinding.itemLayoutIngr.setOnClickListener(
            {
                Toast.makeText(mycontext, "ingr "+ingr.strIngredient!!, Toast.LENGTH_LONG)
                fragmentContatiner.switchToIngredientFragment(ingr.strIngredient!!)
            }
        )
    }

}