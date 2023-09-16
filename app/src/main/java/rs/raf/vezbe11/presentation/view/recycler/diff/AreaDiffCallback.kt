package rs.raf.vezbe11.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity

class AreaDiffCallback : DiffUtil.ItemCallback<AreaEntity>() {

    override fun areItemsTheSame(oldItem: AreaEntity, newItem: AreaEntity): Boolean {
        return oldItem.idArea == newItem.idArea
    }

    override fun areContentsTheSame(oldItem: AreaEntity, newItem: AreaEntity): Boolean {
        return oldItem.strArea == newItem.strArea
    }
}