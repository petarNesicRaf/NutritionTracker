package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.IngredientState

interface IngredientContract {
    interface ViewModel {

        val ingredientState: LiveData<IngredientState>

        fun fetchAll()
        fun getAllIngredients()

    }
}