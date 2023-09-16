package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.data.models.SaveEntity

@Dao
abstract class DetailDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun save(entity: SaveEntity): Completable

}