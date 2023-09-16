package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.MealAreaEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity

sealed class MealAreaState{
    object Loading: MealAreaState()
    object DataFetched: MealAreaState()
    data class Success(val meals: List<MealAreaEntity>): MealAreaState()
    data class Error(val message: String): MealAreaState()
}
