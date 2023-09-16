package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity

@Dao
abstract class MealIngredientDao {
    @Query("SELECT * FROM meals_ingredients WHERE ingredientName LIKE :ingredient_query")
    abstract  fun getMealsByIngredient(ingredient_query: String): Observable<List<MealIngredientEntity>>

    @Query("DELETE FROM meals_ingredients")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<MealIngredientEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<MealIngredientEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

}