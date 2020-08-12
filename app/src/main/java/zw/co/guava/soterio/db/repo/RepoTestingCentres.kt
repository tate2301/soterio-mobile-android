package zw.co.guava.soterio.db.repo

import androidx.lifecycle.LiveData
import zw.co.guava.soterio.db.dao.DaoTestingCentres
import zw.co.guava.soterio.db.entity.EntityTestingCentre

class RepoTestingCentres(private val daoTestingCentres: DaoTestingCentres) {
    val allTestingCentres: LiveData<List<EntityTestingCentre>> = daoTestingCentres.allTestingCentres()


    suspend fun deleteTestingCentres() {
        daoTestingCentres.deleteTestingCentres()
    }


    fun saveAllTestingCentres(TestingCentres: List<EntityTestingCentre>) {
        daoTestingCentres.saveAllTestingCentres(TestingCentres)
    }
}