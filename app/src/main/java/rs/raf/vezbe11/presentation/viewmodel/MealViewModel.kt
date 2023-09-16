package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.MealRepository
import rs.raf.vezbe11.presentation.contract.MealContract
import rs.raf.vezbe11.presentation.contract.UserContract
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MealState
import timber.log.Timber

class MealViewModel(
    private val mealRepository: MealRepository
) : ViewModel(), MealContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val mealState: MutableLiveData<MealState> = MutableLiveData()

    override fun fetchAll(category:String) {
        val subscription = mealRepository
            .fetchAll(category)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealState.value = MealState.Loading
                        is Resource.Success -> mealState.value = MealState.DataFetched
                        is Resource.Error -> mealState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mealState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getMealsByCategory(category: String) {
        val subscription = mealRepository
            .getMealsByCategory(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    mealState.value = MealState.Success(it)
                },
                {
                    mealState.value = MealState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }


}