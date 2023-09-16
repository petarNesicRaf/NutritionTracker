package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.PlanEntity

sealed class AddPlanState {
    object Success: AddPlanState()
    data class Error(val message: String): AddPlanState()
}