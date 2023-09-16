package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity

@Dao
abstract class AreaDao {
    @Query("DELETE FROM areas")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<AreaEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<AreaEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM areas")
    abstract fun getAllAreas(): Observable<List<AreaEntity>>

}