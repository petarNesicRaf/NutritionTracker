package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.MovieRepository
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MoviesState
import timber.log.Timber

class CategoryViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel(), CategoryContract.ViewModel
 {
     private val subscriptions = CompositeDisposable()
     override val categoryState: MutableLiveData<CategoryState> = MutableLiveData()
     override val addCategory: MutableLiveData<AddCategoryState> = MutableLiveData()

     override fun fetchAll() {
         val subscription = categoryRepository
             .fetchAll()
             .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(
                 {
                     when(it) {
                         is Resource.Loading -> categoryState.value = CategoryState.Loading
                         is Resource.Success -> categoryState.value = CategoryState.DataFetched
                         is Resource.Error -> categoryState.value = CategoryState.Error("Error happened while fetching data from the server")
                     }
                 },
                 {
                     categoryState.value = CategoryState.Error("Error happened while fetching data from the server")
                     Timber.e(it)
                 }
             )
         subscriptions.add(subscription)
     }

     override fun getAllCategories() {
         val subscription = categoryRepository
             .getAll()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(
                 {
                     categoryState.value = CategoryState.Success(it)
                 },
                 {
                     categoryState.value = CategoryState.Error("Error happened while fetching data from db")
                     Timber.e(it)
                 }
             )
         subscriptions.add(subscription)
     }
     override fun onCleared() {
         super.onCleared()
         subscriptions.dispose()
     }
 }