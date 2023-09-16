package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.CategoryDao
import rs.raf.vezbe11.data.datasources.local.MealDao
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.datasources.remote.MealService
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class MealRepositoryImpl(
    private val localDataSource: MealDao,
    private val remoteDataSource: MealService
):MealRepository {
    override fun fetchAll(category:String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getMealsByCategory(category)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.meals.map{ meal->
                    MealEntity(
                        meal.idMeal,
                        meal.strMeal,
                        meal.strMealThumb,
                        category
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getMealsByCategory(category:String): Observable<List<MealEntity>> {
        return localDataSource
            .getMealsByCategory(category)
            .map {
                it.map {
                    MealEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strMealThumb,
                        category
                    )
                }
            }
    }
}