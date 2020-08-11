package zw.co.guava.soterio.db.repo

import zw.co.guava.soterio.db.dao.DaoEncounter
import zw.co.guava.soterio.db.entity.EntityEncounter

class RepoEncounters(private val daoEncounter: DaoEncounter) {
    suspend fun saveEncounter(token: EntityEncounter) {
        daoEncounter.saveEncounter(token)
    }

    suspend fun saveAllEncounters(tokens: List<EntityEncounter>) {
        daoEncounter.saveAllEncounters(tokens)
    }
}