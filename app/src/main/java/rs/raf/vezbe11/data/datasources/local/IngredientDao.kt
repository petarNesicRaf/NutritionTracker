package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.IngredientEntity
@Dao
abstract class IngredientDao {
    @Query("DELETE FROM ingredients")
    abstract fun deleteAll()

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<IngredientEntity>): Completable

    @Transaction
    open fun deleteAndInsertAll(entities: List<IngredientEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM ingredients")
    abstract fun getAllIngredients(): Observable<List<IngredientEntity>>


}