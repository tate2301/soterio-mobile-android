package zw.co.soterio.monitor.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityToken
import zw.co.soterio.monitor.storage.entity.Case

@Dao
interface CaseDao {
    @Query("SELECT * FROM cases ORDER BY tul")
    fun getAllCases(): List<EntityToken>

    @Query("SELECT * FROM cases WHERE tll <= :time AND :time <= tul")
    suspend fun getActiveToken(time: Long): EntityToken

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCase(token: EntityToken)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCases(tokens: List<EntityToken>)

    @Query("DELETE FROM cases WHERE tul < :time")
    suspend fun cleanUpCases(time: Long)

    @Query("DELETE FROM cases")
    suspend fun nukeCases()
}