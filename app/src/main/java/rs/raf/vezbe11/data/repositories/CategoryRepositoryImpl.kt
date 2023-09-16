package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.CategoryDao
import rs.raf.vezbe11.data.datasources.remote.CategoryService
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.MovieEntity
import rs.raf.vezbe11.data.models.Resource
import timber.log.Timber

class CategoryRepositoryImpl(
    private val localDataSource:CategoryDao,
    private val remoteDataSource:CategoryService
) : CategoryRepository{
    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getCategories()
            .doOnNext {
                Timber.e("Upis u bazu")
                val entities = it.categories.map{ category->
                    CategoryEntity(
                        category.idCategory,
                        category.strCategory,
                        category.strCategoryThumb,
                        category.strCategoryDescription
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getAll(): Observable<List<CategoryEntity>> {
        return localDataSource
            .getAll()
            .map {
                it.map {
                    CategoryEntity(it.idCategory, it.strCategory, it.strCategoryThumb, it.strCategoryDescription)
                }
            }
    }


}