package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.Resource

interface MealIngredientRepository {


    fun fetchAll(ingredientName:String): Observable<Resource<Unit>>

    fun getMealsByIngredient(ingredientName:String): Observable<List<MealIngredientEntity>>
}