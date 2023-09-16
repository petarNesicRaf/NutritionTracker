package rs.raf.vezbe11.presentation.view.states

import rs.raf.vezbe11.data.models.DateCount

sealed class DateCountState{
        data class Success(val weeklyCount: List<DateCount>): DateCountState()
        data class Error(val message: String): DateCountState()
}
