package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.IngredientRepository
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.contract.IngredientContract
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.IngredientState
import timber.log.Timber

class IngredientViewModel(
    private val ingredientRepository: IngredientRepository
) : ViewModel(), IngredientContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val ingredientState: MutableLiveData<IngredientState> = MutableLiveData()

    override fun fetchAll() {
        val subscription = ingredientRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> ingredientState.value = IngredientState.Loading
                        is Resource.Success -> ingredientState.value = IngredientState.DataFetched
                        is Resource.Error -> ingredientState.value = IngredientState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    ingredientState.value = IngredientState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllIngredients() {
        val subscription = ingredientRepository
            .getAllIngredients()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    ingredientState.value = IngredientState.Success(it)
                },
                {
                    ingredientState.value = IngredientState.Error("Error happened while fetching data from db")
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