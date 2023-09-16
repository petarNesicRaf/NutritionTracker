package rs.raf.vezbe11.presentation.view.states

sealed class AddMealDayState {
    object Success: AddMealDayState()
    data class Error(val message: String): AddMealDayState()
}