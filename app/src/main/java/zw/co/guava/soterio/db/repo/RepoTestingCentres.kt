package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.dao.DaoTestingCentres
import zw.co.guava.soterio.db.entity.EntityTestingCentre

class RepoTestingCentres(private val daoTestingCentres: DaoTestingCentres) {
    suspend fun allTestingCentres() {
        daoTestingCentres.allTestingCentres()
    }

    suspend fun deleteTestingCentres() {
        daoTestingCentres.deleteTestingCentres()
    }


    suspend fun saveAllTestingCentres(TestingCentres: List<EntityTestingCentre>) {
        daoTestingCentres.saveAllTestingCentres(TestingCentres)
    }
}