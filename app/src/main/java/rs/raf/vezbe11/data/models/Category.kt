package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Category(
    val idCategory : Int,
    val strCategory : String,
    val strCategoryThumb : String,
    val strCategoryDescription : String
)
