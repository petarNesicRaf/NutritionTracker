package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.DateCount
import rs.raf.vezbe11.data.models.SaveEntity

interface SaveRepository  {

    fun save(saveMeal:SaveEntity): Completable

    fun getAllSaved(): Observable<List<SaveEntity>>

    fun deleteById(mealId:String):Completable

    fun update(strId: String, newMealName:String, newMealCategory:String, newMealArea:String, newMealThumb:String, newMealYoutube:String,newTypeOfMeal: String, newDate: String):Completable

    fun getMealCountByLast7Days(): Observable<List<DateCount>>
}