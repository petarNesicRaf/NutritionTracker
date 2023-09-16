package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="meals")
data class MealEntity (
    @PrimaryKey
    val idMeal:Int,
    val strMeal:String,
    val strMealThumb:String,
    val categoryName:String
)