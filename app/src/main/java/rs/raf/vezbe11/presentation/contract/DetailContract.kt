package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.DetailState

interface DetailContract {
    interface ViewModel {

        val detailState: LiveData<DetailState>

        fun fetch(mealId:Int)

    }
}