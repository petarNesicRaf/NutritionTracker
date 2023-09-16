package rs.raf.vezbe11.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity (
    @PrimaryKey
    val idCategory : Int,
    val strCategory : String,
    val strCategoryThumb : String,
    val strCategoryDescription : String
)