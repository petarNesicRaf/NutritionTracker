package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.MealResponse
import rs.raf.vezbe11.data.models.UserEntity

@Dao
abstract class MealDao {
    @Query("SELECT * FROM meals WHERE categoryName LIKE :category_query")
    abstract  fun getMealsByCategory(category_query: String): Observable<List<MealEntity>>

    @Query("DELETE FROM meals")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<MealEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }


}