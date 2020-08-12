package zw.co.guava.soterio.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import zw.co.guava.soterio.db.entity.EntityHospital

@Dao
interface DaoHospitals {
    @Query("SELECT * FROM hospitals")
    fun allHospitals(): LiveData<List<EntityHospital>>

    @Query("SELECT * FROM hospitals WHERE id=:id")
    suspend fun getHospital(id: Int): EntityHospital

    @Query("DELETE FROM hospitals")
    suspend fun deleteHospitals()

    @Insert
    fun saveAllHospitals(hospitals: List<EntityHospital>)


}