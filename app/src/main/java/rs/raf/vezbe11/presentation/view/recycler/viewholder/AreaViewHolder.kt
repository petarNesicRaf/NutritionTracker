package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.Area
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.databinding.LayoutItemAreaBinding
import rs.raf.vezbe11.databinding.LayoutItemMovieBinding
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.RecycleMealFragment

class AreaViewHolder (
    private val itemBinding: LayoutItemAreaBinding,
    private val mycontext: Context,
    private val fragmentManager: FragmentManager,
    private val fragmentContatiner: FragmentFilterContainer
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(area: AreaEntity
    ) {
        itemBinding.areaTv.text = area.strArea
        itemBinding.itemLayoutArea.setOnClickListener(
            {
                fragmentContatiner.switchToSecondFragment(area.strArea)
            }
        )
    }

}