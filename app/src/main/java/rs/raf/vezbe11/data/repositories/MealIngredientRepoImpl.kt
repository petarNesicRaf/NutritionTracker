package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.MealIngredientDao
import rs.raf.vezbe11.data.datasources.remote.MealIngredientService
import rs.raf.vezbe11.data.models.MealEntity
import rs.raf.vezbe11.data.models.MealIngredientEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class MealIngredientRepoImpl(
    private val localDataSource:MealIngredientDao,
    private val remoteDataSource:MealIngredientService,
):MealIngredientRepository {
    override fun fetchAll(ingredientName: String): Observable<Resource<Unit>> {
        return remoteDataSource
            .getMealsByIngredient(ingredientName)
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.meals.map{ meal->
                    MealIngredientEntity(
                        meal.idMeal,
                        meal.strMeal,
                        meal.strMealThumb,
                        ingredientName
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getMealsByIngredient(ingredientName: String): Observable<List<MealIngredientEntity>> {
        return localDataSource
            .getMealsByIngredient(ingredientName)
            .map {
                it.map {
                    MealIngredientEntity(
                        it.idMeal,
                        it.strMeal,
                        it.strMealThumb,
                        ingredientName
                    )
                }
            }
    }
}