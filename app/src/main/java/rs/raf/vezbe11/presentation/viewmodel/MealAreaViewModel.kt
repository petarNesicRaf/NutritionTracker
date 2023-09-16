package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.MealAreaRepository
import rs.raf.vezbe11.data.repositories.MealRepository
import rs.raf.vezbe11.presentation.contract.MealAreaContract
import rs.raf.vezbe11.presentation.view.states.MealAreaState
import rs.raf.vezbe11.presentation.view.states.MealIngredientState
import rs.raf.vezbe11.presentation.view.states.MealState
import timber.log.Timber

class MealAreaViewModel(
    private val mealAreaRepository: MealAreaRepository
) : ViewModel(), MealAreaContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    //mealareastate
    override val mealAreaState: MutableLiveData<MealState> = MutableLiveData()

    override fun fetchAll(area: String) {
        val subscription = mealAreaRepository
            .fetchAll(area)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> mealAreaState.value = MealState.Loading
                        is Resource.Success -> mealAreaState.value = MealState.DataFetched
                        is Resource.Error -> mealAreaState.value = MealState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    mealAreaState.value = MealState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)    }

    override fun getMealsByArea(area: String) {
        val subscription = mealAreaRepository
            .getMealsByArea(area)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val mealEntities = it.map { mealAreaEntity ->
                        MealEntity(
                        idMeal = mealAreaEntity.idMeal,
                        strMeal = mealAreaEntity.strMeal,
                            strMealThumb = mealAreaEntity.strMealThumb,
                            categoryName = area
                        )
                    }
                    mealAreaState.value = MealState.Success(mealEntities)
                },
                {
                    mealAreaState.value = MealState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }
}