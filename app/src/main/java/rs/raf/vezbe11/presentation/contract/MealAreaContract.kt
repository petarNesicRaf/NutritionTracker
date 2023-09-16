package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.MealAreaState
import rs.raf.vezbe11.presentation.view.states.MealIngredientState
import rs.raf.vezbe11.presentation.view.states.MealState

interface MealAreaContract {
    interface ViewModel{

        val mealAreaState: LiveData<MealState>
        fun fetchAll(area:String)
        fun getMealsByArea(area: String)

    }
}