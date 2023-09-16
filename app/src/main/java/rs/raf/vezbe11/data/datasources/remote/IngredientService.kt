package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.vezbe11.data.models.CategoryResponse
import rs.raf.vezbe11.data.models.IngredientResponse

interface IngredientService {

    @GET("list.php?i=list")
    fun getIngredients(): Observable<IngredientResponse>


}