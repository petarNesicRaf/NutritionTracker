package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals_ingredients")
data class MealIngredientEntity (
    @PrimaryKey
    val idMeal:Int,
    val strMeal:String,
    val strMealThumb:String,
    val ingredientName:String
)
