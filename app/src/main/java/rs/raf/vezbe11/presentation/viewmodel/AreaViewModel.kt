package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.Resource
import rs.raf.vezbe11.data.repositories.AreaRepository
import rs.raf.vezbe11.presentation.contract.AreaContract
import rs.raf.vezbe11.presentation.view.states.AddCategoryState
import rs.raf.vezbe11.presentation.view.states.AreaState
import rs.raf.vezbe11.presentation.view.states.CategoryState
import timber.log.Timber

class AreaViewModel(
    private val areaRepository:AreaRepository
) : ViewModel(), AreaContract.ViewModel{
    private val subscriptions = CompositeDisposable()
    override val areaState: MutableLiveData<AreaState> = MutableLiveData()

    override fun fetchAll() {
        val subscription = areaRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> areaState.value = AreaState.Loading
                        is Resource.Success -> areaState.value = AreaState.DataFetched
                        is Resource.Error -> areaState.value = AreaState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    areaState.value = AreaState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllAreas() {
        val subscription = areaRepository
            .getAllAreas()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    areaState.value = AreaState.Success(it)
                },
                {
                    areaState.value = AreaState.Error("Error happened while fetching data from db")
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