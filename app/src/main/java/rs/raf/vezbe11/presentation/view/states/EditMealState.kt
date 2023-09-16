package rs.raf.vezbe11.presentation.view.states

sealed class EditMealState {

    object Success: EditMealState()
    data class Error(val message: String): EditMealState()
}