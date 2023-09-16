package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.MealResponse

interface MealIngredientService {

    @GET("filter.php")
    fun getMealsByIngredient(@Query("i") ingredient:String): Observable<MealResponse>
}