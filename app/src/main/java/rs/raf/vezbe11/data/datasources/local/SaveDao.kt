package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.DateCount
import rs.raf.vezbe11.data.models.MovieEntity
import rs.raf.vezbe11.data.models.SaveEntity

@Dao
abstract class SaveDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun save(entity: SaveEntity): Completable

    @Query("SELECT * FROM saved_meals")
    abstract fun getAll(): Observable<List<SaveEntity>>

    @Query("DELETE FROM saved_meals WHERE idMeal = :strId")
    abstract fun deleteById(strId: String): Completable

    @Query("UPDATE saved_meals SET strMeal = :newMealName, strCategory = :newMealCategory,strArea= :newMealArea, strMealThumb = :newMealThumb,strYoutube = :newMealYoutube, strTypeOfMeal = :newTypeOfMeal, strDate = :newDate WHERE idMeal = :strId")
    abstract fun update(strId: String, newMealName:String, newMealCategory:String, newMealArea:String, newMealThumb:String, newMealYoutube:String,newTypeOfMeal: String, newDate: String): Completable

    @Query("SELECT (7 - (julianday('now') - julianday(datetime(strDate, 'unixepoch'))) + 1) AS dateNumber, COUNT(*) AS count FROM saved_meals WHERE strDate >= strftime('%Y-%m-%d', 'now', '-7 days') GROUP BY dateNumber ORDER BY dateNumber DESC")
    abstract fun getMealCountByLast7Days(): Observable<List<DateCount>>

    @Query("SELECT (7 - (julianday('now') - julianday(datetime(strDate, 'unixepoch'))) + 1) AS dateNumber, COUNT(*) AS count FROM saved_meals WHERE strDate >= strftime('%Y-%m-%d', 'now', '-7 days') GROUP BY dateNumber ORDER BY dateNumber DESC")
    abstract fun getMealCountData(): List<DateCount>


}