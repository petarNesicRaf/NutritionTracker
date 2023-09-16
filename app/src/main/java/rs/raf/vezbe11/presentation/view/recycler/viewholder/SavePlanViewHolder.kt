package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat
import org.koin.androidx.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import rs.raf.vezbe11.R
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.LayoutItemDayBinding
import rs.raf.vezbe11.databinding.LayoutItemMealBinding
import rs.raf.vezbe11.databinding.LayoutItemPlanMealBinding
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.contract.PlanContract
import rs.raf.vezbe11.presentation.view.fragments.CategoryFragmentCollection
import rs.raf.vezbe11.presentation.view.fragments.FragmentFilterContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalFragment
import rs.raf.vezbe11.presentation.view.fragments.RecycleSaveFragment
import rs.raf.vezbe11.presentation.view.recycler.adapter.MiniAdapter
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.AddPlanState
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import timber.log.Timber

class SavePlanViewHolder (
    private val itemBinding: LayoutItemPlanMealBinding,
    private val mycontext: Context,
    private val planLocalFragment: PlanLocalContainer?,
    private val planViewModel: PlanViewModel,
    private val vl:LifecycleOwner,
    private val day:String,
    private val planIdd:String,
    private val r:RecycleSaveFragment,
    private val itemv: View
) : RecyclerView.ViewHolder(itemBinding.root) {

    //private val planViewModel: PlanContract.ViewModel by sharedViewModel<PlanViewModel>
      var planId:Long = 0

    fun bind(meal: SaveEntity) {
        itemBinding.mealName.text = meal.strMeal

        //slika
        Glide.with(mycontext)
            .load(meal.strMealThumb)
            .into(itemBinding.image);

        planViewModel.addPlanState.observe(vl, Observer {
            renderState(it)
        })
        planViewModel.planId.observe(vl, Observer{
            planId = it
        })


        itemBinding.btnAdd.setOnClickListener({
            if(day=="monday") {
                planViewModel.addToMonday(planIdd, meal.idMeal)
                val dynamicTextView = itemv.findViewById<View>(R.id.monday).findViewById<TextView>(R.id.dayTv)

                // Set the text of the TextView to the dynamic string
                dynamicTextView.text = meal.strMeal!!.toUpperCase()
                r.goBackToPlanFragment(planIdd)

            }else if(day=="tuesday")
            {
                planViewModel.addToTuesday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }else if(day=="wednesday"){
                planViewModel.addToWednesday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }else if(day=="thursday")
            {
                planViewModel.addToThursday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }else if(day=="friday")
            {
                planViewModel.addToFriday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }else if(day=="saturday")
            {
                planViewModel.addToSaturday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }else{
                planViewModel.addToSunday(planIdd, meal.idMeal)
                r.goBackToPlanFragment(planIdd)

            }

        })
    }

    private fun renderState(state: AddPlanState) {
        when(state) {
            is AddPlanState.Success->
                {
                     Toast.makeText(mycontext, "Meal added to monday", Toast.LENGTH_SHORT)
                .show()

                }
            is AddPlanState.Error -> Toast.makeText(mycontext, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }


}
