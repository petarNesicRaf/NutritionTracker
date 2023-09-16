package rs.raf.vezbe11.data.datasources.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.UserEntity

@Dao
abstract class UserDao {
    @Insert
     abstract fun insert(user: UserEntity):Completable

    @Query("SELECT * FROM users ORDER BY id DESC")
     abstract fun getAllUsers(): Observable<List<UserEntity>>

    @Query("SELECT * FROM users WHERE user_name LIKE :userName")
     abstract  fun getUsername(userName: String): Observable<UserEntity?>
}