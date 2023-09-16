package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.remote.DetailMealService
import rs.raf.vezbe11.data.models.DetailResponse

class DetailRepositoryImpl(
    private val remoteDataSource:DetailMealService
):DetailRepository {
    override fun fetch(mealId: Int): Observable<DetailResponse>{
        return remoteDataSource.getMealDetail(mealId)

    }


}