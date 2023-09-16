package rs.raf.vezbe11.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.PlanEntity
import rs.raf.vezbe11.data.models.SaveEntity

@Dao
abstract class PlanDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertPlan(entity: PlanEntity): Completable

    @Query("UPDATE plans SET monday = :updatedList WHERE strPlanName = :id")
    abstract fun updateMonday(id: String, updatedList: List<String>): Completable

    @Query("UPDATE plans SET tuesday = :updatedList WHERE strPlanName = :id")
    abstract fun updateTuesday(id: String, updatedList: List<String>): Completable
    @Query("UPDATE plans SET wednesday = :updatedList WHERE strPlanName = :id")
    abstract fun updateWednesday(id: String, updatedList: List<String>): Completable
    @Query("UPDATE plans SET thursday = :updatedList WHERE strPlanName = :id")
    abstract fun updateThursday(id: String, updatedList: List<String>): Completable
    @Query("UPDATE plans SET friday = :updatedList WHERE strPlanName = :id")
    abstract fun updateFriday(id: String, updatedList: List<String>): Completable
    @Query("UPDATE plans SET saturday = :updatedList WHERE strPlanName = :id")
    abstract fun updateSaturday(id: String, updatedList: List<String>): Completable
    @Query("UPDATE plans SET sunday = :updatedList WHERE strPlanName = :id")
    abstract fun updateSunday(id: String, updatedList: List<String>): Completable


    @Query("SELECT * FROM plans WHERE strPlanName = :planId")
    abstract fun getPlanById(planId: String): Maybe<PlanEntity>

}