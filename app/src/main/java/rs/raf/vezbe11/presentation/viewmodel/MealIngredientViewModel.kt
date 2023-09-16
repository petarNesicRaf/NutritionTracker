package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.CategoryRepository
import rs.raf.vezbe11.data.repositories.MealIngredientRepository
import rs.raf.vezbe11.presentation.contract.CategoryContract
import rs.raf.vezbe11.presentation.contract.MealIngredientContract
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.MealIngredientState
import rs.raf.vezbe11.presentation.view.states.MealState
import timber.log.Timber

class MealIngredientViewModel(private val mealIngredientRepo: MealIngredientRepository
) : ViewModel(), MealIngredientContract.ViewModel {
    private val subscriptions = CompositeDisposable()
    override val mealState: MutableLiveData<MealState> = MutableLiveData()

    override fun fetchAll(ingredient:String) {
        val subscription = mealIngredientRepo
            .fetchAll(ingredient)
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

    override fun getMealsByIngredient(ingredient: String) {
        val subscription = mealIngredientRepo
            .getMealsByIngredient(ingredient)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val mealEntities = it.map { mealingredientEntity ->
                    MealEntity(
                        idMeal = mealingredientEntity.idMeal,
                        strMeal = mealingredientEntity.strMeal,
                        strMealThumb = mealingredientEntity.strMealThumb,
                        categoryName = ingredient
                    )
                }
                mealState.value = MealState.Success(mealEntities)
            },
                {
                    mealState.value = MealState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
    }
}



