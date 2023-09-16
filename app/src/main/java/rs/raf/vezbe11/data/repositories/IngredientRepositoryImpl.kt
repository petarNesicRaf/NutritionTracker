package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.IngredientDao
import rs.raf.vezbe11.data.datasources.remote.IngredientService
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.IngredientEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class IngredientRepositoryImpl(
    private val localDataSource: IngredientDao,
    private val remoteDataSource: IngredientService
):IngredientRepository {
    override fun fetchAll(): Observable<Resource<Unit>> {
            return remoteDataSource
                .getIngredients()
                .doOnNext {
                    Timber.e("Upis u bazu")
                    val entities = it.meals.map{ ingr->
                        IngredientEntity(
                            ingr.idIngredient,
                            ingr.strIngredient,
                            ingr.strDescription,
                            ingr.strType
                        )
                    }
                    localDataSource.deleteAndInsertAll(entities)
                }
                .map {
                    Resource.Success(Unit)
                }
    }

    override fun getAllIngredients(): Observable<List<IngredientEntity>> {
        return localDataSource
            .getAllIngredients()
            .map {
                it.map {
                    IngredientEntity(it.idIngredient, it.strIngredient, it.strDescription, it.strType)
                }
            }
    }
}