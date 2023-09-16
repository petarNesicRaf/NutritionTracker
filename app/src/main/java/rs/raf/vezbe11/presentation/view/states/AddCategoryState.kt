package rs.raf.vezbe11.presentation.view.states

sealed class AddCategoryState{
    object Success: AddCategoryState()
    data class Error(val message: String): AddCategoryState()
}
