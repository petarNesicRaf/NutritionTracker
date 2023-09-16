package rs.raf.vezbe11.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.PlanEntity
import rs.raf.vezbe11.data.models.Week
import rs.raf.vezbe11.data.repositories.PlanRepository
import rs.raf.vezbe11.presentation.contract.PlanContract
import rs.raf.vezbe11.presentation.view.states.AddMealDayState
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.AddPlanState
import rs.raf.vezbe11.presentation.view.states.MealState
import rs.raf.vezbe11.presentation.view.states.PlanStateGet
import timber.log.Timber

class PlanViewModel(
    private val planRepository:PlanRepository
) :ViewModel(), PlanContract.ViewModel{
    override val addPlanState: MutableLiveData<AddPlanState> = MutableLiveData()

    override val currentPlan: MutableLiveData<PlanEntity> = MutableLiveData()
    override val currentWeek: MutableLiveData<Week> = MutableLiveData()
    override val currentDayOfTheWeek: MutableLiveData<String> = MutableLiveData()

    override val getState: MutableLiveData<PlanStateGet> = MutableLiveData()
    override val addMealDayState: MutableLiveData<AddMealDayState> = MutableLiveData()
    override val planId: MutableLiveData<Long> = MutableLiveData()


    private val subscriptions = CompositeDisposable()


    override fun insertPlan(planEntity: PlanEntity) {
        val subscription = planRepository
            .insertPlan(planEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    //var id:Long =  AddPlanState.Success
                    addPlanState.value = AddPlanState.Success
                    //planId.value =  id
                },
                {
                    addPlanState.value = AddPlanState.Error("Error happened while adding plan")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun addToMonday(planId: String,  meal:String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                updatedList.add(meal)

                // Update the PlanEntity with the new list
                planRepository.updateMonday(planId, updatedList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // Handle success
                               addMealDayState.value = AddMealDayState.Success
                    }, {
                        // Handle error
                        addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                    })
            })
        subscriptions.add(disposable)
    }

    override fun addToTuesday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateTuesday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun addToWednesday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateWednesday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun addToThursday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateThursday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun addToFriday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateFriday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun addToSaturday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateSaturday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun addToSunday(planId: String, meal: String) {
        val disposable = planRepository
            .getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    val updatedList = it.monday?.toMutableList() ?: mutableListOf()
                    updatedList.add(meal)

                    // Update the PlanEntity with the new list
                    planRepository.updateSunday(planId, updatedList)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            // Handle success
                            addMealDayState.value = AddMealDayState.Success
                        }, {
                            // Handle error
                            addMealDayState.value = AddMealDayState.Error("Error while adding meal to monday")
                        })
                })
        subscriptions.add(disposable)    }

    override fun getPlanById(planId: String) {
        val subscriber = planRepository.getPlanById(planId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                getState.value = PlanStateGet.Success(it)
            }, {
                getState.value = PlanStateGet.Error("Error happened while getting plan by id")
            })

        subscriptions.add(subscriber)
    }
}