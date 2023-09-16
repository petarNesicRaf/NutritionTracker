package rs.raf.vezbe11.data.repositories

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.UserEntity

interface UserRepository {
      fun insert(user: UserEntity): Completable

      fun getAllUsers(): Observable<List<UserEntity>>

      fun getUsername(userName: String): Observable<UserEntity?>
}