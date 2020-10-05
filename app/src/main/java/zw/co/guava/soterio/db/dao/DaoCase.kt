package zw.co.guava.soterio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityCase

@Dao
interface DaoCase {
    @Query("SELECT * FROM cases ORDER BY tul")
    fun getAllCases(): List<EntityCase>

    @Query("SELECT * FROM cases WHERE tll <= :time AND :time <= tul")
    suspend fun getActiveCase(time: Long): EntityCase

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCase(Case: EntityCase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllCases(Cases: List<EntityCase>)

    @Query("DELETE FROM cases WHERE tul < :time")
    suspend fun cleanUpCases(time: Long)

    @Query("DELETE FROM cases")
    suspend fun nukeCases()
}