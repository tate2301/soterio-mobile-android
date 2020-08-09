package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.dao.DaoTokens
import zw.co.guava.soterio.db.entity.EntityToken

class RepoTokens(private val daoTokens: DaoTokens) {
    suspend fun saveToken(token:EntityToken) {
        daoTokens.saveToken(token)
    }

    suspend fun saveAllTokens(tokens: List<EntityToken>) {
        daoTokens.saveAllTokens(tokens)
    }
}