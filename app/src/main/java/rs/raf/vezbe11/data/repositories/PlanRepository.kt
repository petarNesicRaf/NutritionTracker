package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.vezbe11.data.models.PlanEntity

interface PlanRepository {

    fun insertPlan(planEntity: PlanEntity):Completable

    fun updateMonday(planId: String, updatedList: List<String>):Completable
    fun updateTuesday(planId: String, updatedList: List<String>):Completable
    fun updateWednesday(planId: String, updatedList: List<String>):Completable
    fun updateThursday(planId: String, updatedList: List<String>):Completable
    fun updateFriday(planId: String, updatedList: List<String>):Completable
    fun updateSaturday(planId: String, updatedList: List<String>):Completable
    fun updateSunday(planId: String, updatedList: List<String>):Completable

    fun getPlanById(planName: String): Maybe<PlanEntity>
}