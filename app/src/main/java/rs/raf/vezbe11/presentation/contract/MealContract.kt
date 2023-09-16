package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MealState

interface MealContract {
    interface ViewModel {

        val mealState: LiveData<MealState>

        fun fetchAll(category:String)
        fun getMealsByCategory(category: String)

    }
}