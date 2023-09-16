package rs.raf.vezbe11.presentation.view.recycler.viewholder

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.databinding.LayoutItemPlanMealBinding
import rs.raf.vezbe11.databinding.LayoutItemRemoteMealBinding
import rs.raf.vezbe11.presentation.view.fragments.PlanLocalContainer
import rs.raf.vezbe11.presentation.view.fragments.PlanRemoteContainer
import rs.raf.vezbe11.presentation.view.fragments.RecycleMealFragment
import rs.raf.vezbe11.presentation.view.states.AddPlanState
import rs.raf.vezbe11.presentation.viewmodel.PlanViewModel
import timber.log.Timber

class RemoteMealViewHolder(
    private val itemBinding: LayoutItemRemoteMealBinding,
    private val mycontext: Context,
    private val planLocalFragment: PlanRemoteContainer?,
    private val planViewModel: PlanViewModel,
    private val vl: LifecycleOwner,
    private val day:String,
    private val planIdd:String,
    private val r: RecycleMealFragment
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(meal: MealEntity) {
        itemBinding.mealName.text = meal.strMeal

        //slika
        Glide.with(mycontext)
            .load(meal.strMealThumb)
            .into(itemBinding.image);

        planViewModel.addPlanState.observe(vl, Observer {
            renderState(it)
        })
        planViewModel.planId.observe(vl, Observer{
            //planId = it
        })

        itemBinding.btnAdd.setOnClickListener({
            Timber.e("lelele " + meal.strMeal +" " + meal.idMeal)
            if(day=="monday") {
                planViewModel.addToMonday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else if(day=="tuesday")
            {
                planViewModel.addToTuesday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else if(day=="wednesday"){
                planViewModel.addToWednesday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else if(day=="thursday")
            {
                planViewModel.addToThursday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else if(day=="friday")
            {
                planViewModel.addToFriday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else if(day=="saturday")
            {
                planViewModel.addToSaturday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }else{
                planViewModel.addToSunday(planIdd, meal.idMeal.toString())
                r.goBackToPlanFragment()
            }

        })
    }

    private fun renderState(state: AddPlanState) {
        when(state) {
            is AddPlanState.Success -> Toast.makeText(mycontext, "Meal added to monday", Toast.LENGTH_SHORT)
                .show()
            is AddPlanState.Error -> Toast.makeText(mycontext, "Error happened", Toast.LENGTH_SHORT)
                .show()
        }
    }

}
