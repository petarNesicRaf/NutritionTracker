package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity

sealed class AreaState {
    object Loading: AreaState()
    object DataFetched: AreaState()
    data class Success(val areas: List<AreaEntity>): AreaState()
    data class Error(val message: String): AreaState()
}