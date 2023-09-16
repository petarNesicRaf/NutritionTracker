package rs.raf.vezbe11.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.data.models.MealAreaEntity
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.MovieEntity
import rs.raf.vezbe11.data.models.PlanEntity
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.data.models.UserEntity
import rs.raf.vezbe11.data.models.converters.ListConverter
import rs.raf.vezbe11.data.models.converters.PlanEntityConverter
import rs.raf.vezbe11.data.models.converters.SaveEntityConverter
import rs.raf.vezbe11.data.models.converters.WeekConverter

@Database(
    entities = [MovieEntity::class, UserEntity::class, CategoryEntity::class,
        MealEntity::class,MealIngredientEntity::class, AreaEntity::class, MealAreaEntity::class,
               IngredientEntity::class, SaveEntity::class, PlanEntity::class]
    ,
    version = 9,
    exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getUserDao(): UserDao
    abstract fun getCategoryDao():CategoryDao
    abstract fun getMealDao():MealDao
    abstract fun getMealIngredientDao():MealIngredientDao
    abstract fun getAreaDao():AreaDao
    abstract fun getMealAreaDao():MealAreaDao
    abstract fun getIngredientDao():IngredientDao
    abstract fun getSaveDeo():SaveDao
    abstract fun getPlanDao():PlanDao
}