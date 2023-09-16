package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Resource

interface MealRepository {

    fun fetchAll(category:String): Observable<Resource<Unit>>

    fun getMealsByCategory(category:String): Observable<List<MealEntity>>
}