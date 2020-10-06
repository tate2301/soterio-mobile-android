package zw.co.guava.soterio.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityExposureKey
import zw.co.guava.soterio.db.entity.EntityToken

@Dao
interface DaoExposureKeys {
    @Query("SELECT * FROM exposure_keys ORDER BY tul")
    fun getAllTokens(): List<EntityExposureKey>

    @Query("SELECT * FROM exposure_keys WHERE tll <= :time AND :time <= tul")
    suspend fun getActiveToken(time: Long): EntityExposureKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToken(token: EntityExposureKey)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTokens(tokens: List<EntityExposureKey>)

    @Query("DELETE FROM tokens WHERE tul < :time")
    suspend fun cleanUp(time: Long)

    @Query("DELETE FROM tokens")
    suspend fun nukeTokens()
}