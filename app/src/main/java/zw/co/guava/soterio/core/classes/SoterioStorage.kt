package zw.co.guava.soterio.core.classes

import android.content.Context
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.dao.DaoEncounter
import zw.co.guava.soterio.db.dao.DaoTokens
import zw.co.guava.soterio.db.entity.EntityEncounter
import zw.co.guava.soterio.db.entity.EntityToken

class SoterioStorage(val context: Context) {
    private val tokenDao: DaoTokens = CoreDatabase.getDatabase(context).daoTokens()
    private val encounterDao: DaoEncounter = CoreDatabase.getDatabase(context).daoEncounter()

    suspend fun saveRecord(token: EntityToken) {
        tokenDao.saveToken(token)
    }

    suspend fun cleanUpRecords(timeInMs: Long) {
        return tokenDao.cleanUp(timeInMs)
    }

    suspend fun nukeTokens() {
        tokenDao.nukeTokens()
    }

    suspend fun getAllRecords(): List<EntityToken> {
        return tokenDao.getAllTokens()
    }

    suspend fun getActiveToken(): EntityToken {
        return tokenDao.getActiveToken(System.currentTimeMillis())
    }

    suspend fun saveEncounter(encounter: EntityEncounter) {
        return encounterDao.saveEncounter(encounter)
    }


}