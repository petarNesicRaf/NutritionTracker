package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.MealAreaEntity
import rs.raf.vezbe11.data.models.MealEntity

@Dao
abstract class MealAreaDao {
    @Query("SELECT * FROM meals_areas WHERE areaName LIKE :area")
    abstract  fun getMealsByArea(area: String): Observable<List<MealAreaEntity>>

    @Query("DELETE FROM meals_areas")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<MealAreaEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealAreaEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }
}