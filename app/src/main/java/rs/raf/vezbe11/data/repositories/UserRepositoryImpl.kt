package rs.raf.vezbe11.data.repositories

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.UserDao
import rs.raf.vezbe11.data.models.UserEntity

class UserRepositoryImpl(
    private val localDataSource: UserDao
) : UserRepository{
    override  fun insert(user: UserEntity) :Completable{
        return localDataSource.insert(user)
    }

    override  fun getAllUsers(): Observable<List<UserEntity>> {
        return localDataSource.getAllUsers()
    }

    override  fun getUsername(userName: String): Observable<UserEntity?> {
        return localDataSource.getUsername(userName)
    }

}