package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.MealAreaEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.Resource

interface MealAreaRepository {

    fun fetchAll(areaName:String): Observable<Resource<Unit>>

    fun getMealsByArea(areaName:String): Observable<List<MealAreaEntity>>
}