package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.Resource

interface CategoryRepository {

    fun fetchAll(): Observable<Resource<Unit>>

    fun getAll(): Observable<List<CategoryEntity>>
}