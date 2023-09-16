package rs.raf.vezbe11.presentation.view.states

import io.reactivex.Completable
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.SaveEntity

sealed class SaveState {
    object Loading: SaveState()
    object DataFetched: SaveState()
    data class Success(val save: List<SaveEntity>): SaveState()
    data class Error(val message: String): SaveState()
}