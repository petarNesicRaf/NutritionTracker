package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.CategoryResponse
import rs.raf.vezbe11.data.models.MovieEntity

@Dao
abstract class CategoryDao {
    @Query("DELETE FROM categories")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<CategoryEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<CategoryEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM categories")
    abstract fun getAll(): Observable<List<CategoryEntity>>



}