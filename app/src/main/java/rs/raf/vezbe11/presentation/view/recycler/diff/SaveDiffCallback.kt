package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.SaveEntity

class SaveDiffCallback: DiffUtil.ItemCallback<SaveEntity>() {
    override fun areItemsTheSame(oldItem: SaveEntity, newItem: SaveEntity): Boolean {
        return oldItem.idMeal.toInt() == newItem.idMeal.toInt()
    }

    override fun areContentsTheSame(oldItem: SaveEntity, newItem: SaveEntity): Boolean {
        return oldItem.strMeal == newItem.strMeal
    }

}