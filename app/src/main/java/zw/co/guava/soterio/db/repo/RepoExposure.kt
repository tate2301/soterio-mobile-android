package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.dao.DaoExposureKeys
import zw.co.guava.soterio.db.dao.DaoTokens
import zw.co.guava.soterio.db.entity.EntityExposureKey
import zw.co.guava.soterio.db.entity.EntityToken

class RepoExposure(private val daoExposureKeys: DaoExposureKeys) {
    suspend fun saveToken(token: EntityExposureKey) {
        daoExposureKeys.saveToken(token)
    }

    fun saveAllTokens(tokens: List<EntityExposureKey>) {
        daoExposureKeys.saveAllTokens(tokens)
    }
}