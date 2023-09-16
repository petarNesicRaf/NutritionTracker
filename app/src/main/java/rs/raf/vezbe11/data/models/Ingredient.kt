package rs.raf.vezbe11.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ingredient (
    val idIngredient: Int,
    val strIngredient: String?,
    val strDescription: String?,
    val strType: String?
)