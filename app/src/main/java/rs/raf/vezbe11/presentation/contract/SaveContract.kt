package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.view.states.DateCountState
import rs.raf.vezbe11.presentation.view.states.EditMealState
import rs.raf.vezbe11.presentation.view.states.MoviesState
import rs.raf.vezbe11.presentation.view.states.SaveState

interface SaveContract {

    interface ViewModel {

        val addSaveState: LiveData<AddSaveState>
        val saveState: LiveData<SaveState>
        val editState: LiveData<EditMealState>
        val weeklyCount: LiveData<DateCountState>

        fun save(meal: SaveEntity)
        fun getAllSaved()
        fun deleteSaved(mealId:String)
        fun update(strId: String, newMealName:String, newMealCategory:String, newMealArea:String, newMealThumb:String, newMealYoutube:String,newTypeOfMeal: String, newDate: String)
        fun getWeeklyCount()    }
}