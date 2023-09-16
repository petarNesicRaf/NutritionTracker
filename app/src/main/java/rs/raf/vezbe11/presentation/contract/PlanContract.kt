package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.data.models.PlanEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.data.models.Week
import rs.raf.vezbe11.presentation.view.states.AddMealDayState
import rs.raf.vezbe11.presentation.view.states.AddPlanState
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.view.states.PlanStateGet

interface PlanContract {
    interface ViewModel{
        val addPlanState: LiveData<AddPlanState>
        val currentPlan: LiveData<PlanEntity>
        val currentWeek: LiveData<Week>
        val currentDayOfTheWeek: LiveData<String>
        val getState:LiveData<PlanStateGet>
        val addMealDayState: LiveData<AddMealDayState>
        val planId: LiveData<Long>

        fun insertPlan(planEntity: PlanEntity)
        fun addToMonday(planId: String, meal:String)
        fun addToTuesday(planId: String, meal:String)
        fun addToWednesday(planId: String, meal:String)
        fun addToThursday(planId: String, meal:String)
        fun addToFriday(planId: String, meal:String)
        fun addToSaturday(planId: String, meal:String)
        fun addToSunday(planId: String, meal:String)

        fun getPlanById(planId:String)
    }
}