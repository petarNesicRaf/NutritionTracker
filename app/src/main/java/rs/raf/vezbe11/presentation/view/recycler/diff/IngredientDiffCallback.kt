package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.IngredientEntity

class IngredientDiffCallback : DiffUtil.ItemCallback<IngredientEntity>() {

    override fun areItemsTheSame(oldItem: IngredientEntity, newItem: IngredientEntity): Boolean {
        return oldItem.idIngredient == newItem.idIngredient
    }

    override fun areContentsTheSame(oldItem: IngredientEntity, newItem: IngredientEntity): Boolean {
        return oldItem.strIngredient == newItem.strIngredient
    }
}