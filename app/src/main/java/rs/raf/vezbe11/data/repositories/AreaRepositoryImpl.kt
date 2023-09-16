package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.AreaDao
import rs.raf.vezbe11.data.datasources.remote.AreaService
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class AreaRepositoryImpl(
    val localDataSource:AreaDao,
    val remoteDataSource:AreaService
) : AreaRepository{

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAreas()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.meals.map{ area->
                    AreaEntity(
                        0,
                        area.strArea
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAllAreas(): Observable<List<AreaEntity>> {
        return localDataSource
            .getAllAreas()
            .map {
                it.map {
                    AreaEntity(it.idArea, it.strArea)
                }
            }
    }

}