package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity

sealed class MealIngredientState{
    object Loading: MealIngredientState()
    object DataFetched: MealIngredientState()
    data class Success(val meals: List<MealIngredientEntity>): MealIngredientState()
    data class Error(val message: String): MealIngredientState()
}
