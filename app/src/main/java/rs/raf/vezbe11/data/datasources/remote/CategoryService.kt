package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.vezbe11.data.models.CategoryResponse
import rs.raf.vezbe11.data.models.MovieResponse

interface CategoryService {

    @GET("categories.php")
    fun getCategories(): Observable<CategoryResponse>


}