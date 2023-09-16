package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.MealIngredientState
import rs.raf.vezbe11.presentation.view.states.MealState

interface MealIngredientContract {
    interface ViewModel{

        val mealState: LiveData<MealState>

        fun fetchAll(ingredient:String)
        fun getMealsByIngredient(ingredient: String)
    }
}