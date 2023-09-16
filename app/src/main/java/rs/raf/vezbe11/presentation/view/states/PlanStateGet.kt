package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.PlanEntity

sealed class PlanStateGet {
    object Loading: PlanStateGet()
    object DataFetched: PlanStateGet()
    data class Success(val planEntity: PlanEntity): PlanStateGet()
    data class Error(val message: String): PlanStateGet()
}