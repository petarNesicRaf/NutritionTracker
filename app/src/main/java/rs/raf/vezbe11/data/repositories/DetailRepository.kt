package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.Detail
import rs.raf.vezbe11.data.models.DetailResponse
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.Resource

interface DetailRepository {
    fun fetch(mealId:Int): Observable<DetailResponse>


}