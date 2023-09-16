package rs.raf.vezbe11.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import rs.raf.vezbe11.data.models.AreaResponse
import rs.raf.vezbe11.data.models.CategoryResponse

interface AreaService {
    @GET("list.php?a=list")
    fun getAreas(): Observable<AreaResponse>
}