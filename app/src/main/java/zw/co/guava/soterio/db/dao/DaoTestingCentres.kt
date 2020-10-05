package zw.co.guava.soterio.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityTestingCentre

@Dao
interface DaoTestingCentres {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllTestingCentres(hospitals: List<EntityTestingCentre>)

    @Query("SELECT * FROM testingcentres ORDER BY name")
    fun allTestingCentres(): LiveData<List<EntityTestingCentre>>

    @Query("SELECT * FROM testingcentres WHERE _id=:id")
    suspend fun getTestingCentres(id: Int): EntityTestingCentre

    @Query("DELETE FROM testingcentres")
    suspend fun deleteTestingCentres()
}