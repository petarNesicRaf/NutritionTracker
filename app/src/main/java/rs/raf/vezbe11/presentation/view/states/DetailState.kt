package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.data.models.DetailResponse

sealed class DetailState {
    object Loading: DetailState()
    object DataFetched: DetailState()
    data class Success(val details:DetailResponse): DetailState()
    data class Error(val message: String): DetailState()
}