package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.AreaState
import rs.raf.vezbe11.presentation.view.states.CategoryState

interface AreaContract {
    interface ViewModel {

        val areaState: LiveData<AreaState>

        fun fetchAll()
        fun getAllAreas()

    }
}