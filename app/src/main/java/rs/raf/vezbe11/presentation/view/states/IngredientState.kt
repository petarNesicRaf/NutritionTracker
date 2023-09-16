package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.IngredientEntity

sealed class IngredientState {
    object Loading: IngredientState()
    object DataFetched: IngredientState()
    data class Success(val ingredients: List<IngredientEntity>): IngredientState()
    data class Error(val message: String): IngredientState()
}