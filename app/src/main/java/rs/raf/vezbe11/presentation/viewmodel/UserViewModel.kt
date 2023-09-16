package rs.raf.vezbe11.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.vezbe11.data.models.UserEntity
import rs.raf.vezbe11.data.repositories.UserRepository
import rs.raf.vezbe11.presentation.contract.MainContract
import rs.raf.vezbe11.presentation.contract.UserContract
import rs.raf.vezbe11.presentation.view.states.AddMovieState
import rs.raf.vezbe11.presentation.view.states.MoviesState
import timber.log.Timber

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel(),  UserContract.ViewModel{
    private val subscriptions = CompositeDisposable()

    override var user: MutableLiveData<UserEntity?> = MutableLiveData<UserEntity?>()

    override  fun insert(user: UserEntity) {
         val subscription = userRepository
            .insert(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        subscriptions.add(subscription)
        userRepository.insert(user)
    }

    override   fun getAllUsers(){
    //todo
    }

    override  fun getUsername(userName: String){
        val subscription = userRepository
            .getUsername(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                user.value = it
            }
        subscriptions.add(subscription)

    }

    override fun setUserToNull()
    {
        user.value = null
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}