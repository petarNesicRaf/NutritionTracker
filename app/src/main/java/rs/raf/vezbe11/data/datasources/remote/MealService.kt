package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.MealResponse

interface MealService {

    @GET("filter.php")
    fun getMealsByCategory(@Query("c") category: String): Observable<MealResponse>

}