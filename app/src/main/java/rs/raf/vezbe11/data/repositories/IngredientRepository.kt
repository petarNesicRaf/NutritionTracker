package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.data.models.Resource

interface IngredientRepository {
    fun fetchAll(): Observable<Resource<Unit>>

    fun getAllIngredients(): Observable<List<IngredientEntity>>
}