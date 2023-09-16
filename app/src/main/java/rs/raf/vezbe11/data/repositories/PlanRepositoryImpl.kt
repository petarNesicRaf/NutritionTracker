package rs.raf.vezbe11.data.repositories

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import rs.raf.vezbe11.data.datasources.local.PlanDao
import rs.raf.vezbe11.data.models.PlanEntity

class PlanRepositoryImpl(
    private val localDataSource:PlanDao
):PlanRepository {
    override fun insertPlan(planEntity: PlanEntity): Completable{
        return localDataSource.insertPlan(planEntity)
    }

    override fun updateMonday(planId: String, updatedList: List<String>) :Completable{
        return localDataSource.updateMonday(planId, updatedList)
    }

    override fun updateTuesday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateTuesday(planId, updatedList)
    }

    override fun updateWednesday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateWednesday(planId, updatedList)
    }

    override fun updateThursday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateThursday(planId, updatedList)
    }

    override fun updateFriday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateFriday(planId, updatedList)
    }

    override fun updateSaturday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateSaturday(planId, updatedList)
    }

    override fun updateSunday(planId: String, updatedList: List<String>): Completable {
        return localDataSource.updateSunday(planId, updatedList)
    }

    override fun getPlanById(planName: String): Maybe<PlanEntity> {
        return localDataSource.getPlanById(planName)
    }
}