package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meal(
    val strMeal:String,
    val strMealThumb:String,
    val idMeal:Int,
)
