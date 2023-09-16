package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.data.repositories.SaveRepository
import rs.raf.vezbe11.presentation.contract.SaveContract
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.AddSaveState
import rs.raf.vezbe11.presentation.view.states.DateCountState
import rs.raf.vezbe11.presentation.view.states.EditMealState
import rs.raf.vezbe11.presentation.view.states.MoviesState
import rs.raf.vezbe11.presentation.view.states.SaveState
import timber.log.Timber

class SaveViewModel(
    private val saveRepository: SaveRepository
) : ViewModel(), SaveContract.ViewModel{
    override val addSaveState: MutableLiveData<AddSaveState> = MutableLiveData()
    override val saveState: MutableLiveData<SaveState> = MutableLiveData()
    override val editState: MutableLiveData<EditMealState> = MutableLiveData()
    override val weeklyCount: MutableLiveData<DateCountState> = MutableLiveData()


    private val subscriptions = CompositeDisposable()

    override fun save(meal: SaveEntity) {
        val subscription = saveRepository
            .save(meal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addSaveState.value = AddSaveState.Success
                },
                {
                    addSaveState.value = AddSaveState.Error("Error happened while saving a meal")
                    Timber.e(it)
                }
            )

        subscriptions.add(subscription)
    }

    override fun getAllSaved() {
        val subscription = saveRepository
            .getAllSaved()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    saveState.value = SaveState.Success(it)
                },
                {
                    saveState.value = SaveState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteSaved(mealId: String) {
        val subscription = saveRepository
            .deleteById(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()

        subscriptions.add(subscription)
    }

    override fun update(
        strId: String,
        newMealName: String,
        newMealCategory: String,
        newMealArea: String,
        newMealThumb: String,
        newMealYoutube: String,
        newTypeOfMeal: String,
        newDate: String
    ) {
        val subscription = saveRepository
            .update(strId, newMealName, newMealCategory, newMealArea, newMealThumb, newMealYoutube, newTypeOfMeal, newDate)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    editState.value = EditMealState.Success
                },
                {
                    editState.value = EditMealState.Error("Error happened while saving a meal")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)

    }


    override fun getWeeklyCount() {
        val subscription = saveRepository
            .getMealCountByLast7Days()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    weeklyCount.value = DateCountState.Success(it)
                    Timber.e(it.toString())
                },
                {
                    weeklyCount.value = DateCountState.Error("Error happened while adding movie")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

}