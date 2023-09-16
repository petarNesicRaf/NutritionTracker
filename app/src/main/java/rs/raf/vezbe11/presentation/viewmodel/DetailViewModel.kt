package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.DetailRepository
import rs.raf.vezbe11.presentation.contract.DetailContract
import rs.raf.vezbe11.presentation.view.states.CategoryState
import rs.raf.vezbe11.presentation.view.states.DetailState
import rs.raf.vezbe11.presentation.view.states.IngredientState
import timber.log.Timber

class DetailViewModel(
    private val detailRepository:DetailRepository
) : ViewModel(), DetailContract.ViewModel{

    private val subscriptions = CompositeDisposable()
    override val detailState: MutableLiveData<DetailState> = MutableLiveData()

    override fun fetch(mealId: Int) {

        val subscription = detailRepository
            .fetch(mealId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    detailState.value = DetailState.Success(it)
                },
                {
                    detailState.value = DetailState.Error("Error happened while fetching data from the WEB  " + it)
                    Timber.e("bebe "+it)
                }
            )
        subscriptions.add(subscription)
    }

}