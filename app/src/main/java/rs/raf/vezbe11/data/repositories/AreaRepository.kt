package rs.raf.vezbe11.data.repositories

import io.reactivex.Observable
import rs.raf.vezbe11.data.models.AreaEntity
import rs.raf.vezbe11.data.models.CategoryEntity
import rs.raf.vezbe11.data.models.Resource

interface AreaRepository {
    fun fetchAll(): Observable<Resource<Unit>>

    fun getAllAreas(): Observable<List<AreaEntity>>
}