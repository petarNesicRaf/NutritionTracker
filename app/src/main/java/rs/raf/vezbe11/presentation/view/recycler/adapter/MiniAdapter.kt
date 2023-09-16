package rs.raf.vezbe11.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rs.raf.vezbe11.R

class MiniAdapter(private val mealList: List<String>) : RecyclerView.Adapter<MiniAdapter.MealViewHolder>() {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealNameTextView: TextView = itemView.findViewById(R.id.mealNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_mini, parent, false)
        return MealViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val mealName = mealList[position]
        holder.mealNameTextView.text = mealName
    }

    override fun getItemCount(): Int {
        return mealList.size
    }
}
