package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MoviesState

interface CategoryContract {
    interface ViewModel {

        val categoryState: LiveData<CategoryState>
        val addCategory: LiveData<AddCategoryState>

        fun fetchAll()
        fun getAllCategories()

    }
}