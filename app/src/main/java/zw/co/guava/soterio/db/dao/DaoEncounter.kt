package zw.co.guava.soterio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityEncounter

@Dao
interface DaoEncounter{

    @Query ("SELECT * FROM encounters")
    suspend fun getAllEncounters():List<EntityEncounter>

    @Query ("SELECT * FROM encounters WHERE identifier = :Identifier ORDER BY timestamp DESC LIMIT 1")
    suspend fun getEncounter(Identifier: String): EntityEncounter

    @Query("SELECT * FROM encounters WHERE identifier = :Identifier ")
    suspend fun getEncountersByIdentifier(Identifier: String): List<EntityEncounter>

    @Query("DELETE FROM encounters")
    suspend fun nukeEncounters()

    @Insert
    suspend fun saveEncounter(encounter: EntityEncounter)

    @Insert()
    suspend fun saveAllEncounters(encounters: List<EntityEncounter>)

}