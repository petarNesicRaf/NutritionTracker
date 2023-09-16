package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.SaveDao
import rs.raf.vezbe11.data.models.DateCount
import rs.raf.vezbe11.data.models.Movie
import rs.raf.vezbe11.data.models.MovieEntity
import rs.raf.vezbe11.data.models.SaveEntity

class SaveRepositoryImpl(
    private val localDataSource:SaveDao
) : SaveRepository{
    override fun save(meal: SaveEntity): Completable {
        return localDataSource
            .save(meal)
    }

    override fun getAllSaved(): Observable<List<SaveEntity>> {
        return localDataSource.getAll()
    }

    override fun deleteById(mealId: String): Completable {
        return localDataSource.deleteById(mealId)
    }

    override fun update(
        strId: String,
        newMealName: String,
        newMealCategory: String,
        newMealArea: String,
        newMealThumb: String,
        newMealYoutube: String,
        newTypeOfMeal: String,
        newDate: String
    ):Completable {
        return localDataSource.update(strId, newMealName, newMealCategory, newMealArea, newMealThumb, newMealYoutube, newTypeOfMeal, newDate)
    }

    override fun getMealCountByLast7Days(): Observable<List<DateCount>> {
        return localDataSource.getMealCountByLast7Days()
    }

}