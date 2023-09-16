package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.MealAreaDao
import rs.raf.vezbe11.data.datasources.remote.MealAreaService
import rs.raf.vezbe11.data.models.MealAreaEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class MealAreaRepoImpl(
    private val localDataSource:MealAreaDao,
    private val remoteDataSource:MealAreaService
) :MealAreaRepository{

    override fun fetchAll(areaName: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getMealsByArea(areaName)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.meals.map{ meal->
                    MealAreaEntity(
                        meal.idMeal,
                        meal.strMeal,
                        meal.strMealThumb,
                        areaName
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getMealsByArea(areaName: String): Observable<List<MealAreaEntity>> {
        return localDataSource
            .getMealsByArea(areaName)
            .map {
                it.map {
                    MealAreaEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strMealThumb,
                        areaName
                    )
                }
            }
    }


}