package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Movie

sealed class MealState {
    object Loading: MealState()
    object DataFetched: MealState()
    data class Success(val meals: List<MealEntity>): MealState()
    data class Error(val message: String): MealState()
}