package zw.co.guava.soterio.db.repo

import androidx.lifecycle.LiveData
import zw.co.guava.soterio.Constants
import zw.co.guava.soterio.db.dao.DaoHospitals
import zw.co.guava.soterio.db.entity.EntityHospital

class RepoHospitals(private val daoHospitals: DaoHospitals) {
    val allHospitals: LiveData<List<EntityHospital>> = daoHospitals.allHospitals()


    suspend fun deleteHospitals() {
        daoHospitals.deleteHospitals()
    }


    fun saveAllHospitals(hospitals: List<EntityHospital>) {
        daoHospitals.saveAllHospitals(hospitals)
    }
}