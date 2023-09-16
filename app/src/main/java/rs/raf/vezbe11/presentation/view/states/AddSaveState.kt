package rs.raf.vezbe11.presentation.view.states

sealed class AddSaveState {

        object Success: AddSaveState()
        data class Error(val message: String): AddSaveState()

}