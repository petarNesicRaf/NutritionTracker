package rs.raf.vezbe11.presentation.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.UserEntity

interface UserContract {
    interface ViewModel{
         val user: LiveData<UserEntity?>

         fun insert(user: UserEntity)

         fun getAllUsers()

         fun getUsername(userName: String)

         fun setUserToNull()
    }
}