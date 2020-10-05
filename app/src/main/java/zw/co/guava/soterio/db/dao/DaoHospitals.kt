package zw.co.guava.soterio.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import zw.co.guava.soterio.Constants
import zw.co.guava.soterio.R
import zw.co.guava.soterio.db.entity.EntityHospital

@Dao
interface DaoHospitals {
    @Query("SELECT * FROM hospitals ORDER BY name")
    //@Query("SELECT *, sin(PI()*:lat/180.0)*sin(PI()*latitude/180.0) + COS(PI()*:lat/180.0)*COS(PI()*latitude/180.0)*COS(PI()*longitude/180.0-PI()*:lon/180.0) AS dist FROM hospitals ORDER BY dist DESC")
    fun allHospitals(): LiveData<List<EntityHospital>>

    //@RawQuery
    //fun allHospitals(query: SupportSQLiteQuery): LiveData<List<EntityHospital>>

    @Query("SELECT * FROM hospitals WHERE _id=:id")
    suspend fun getHospital(id: Int): EntityHospital

    @Query("DELETE FROM hospitals")
    suspend fun deleteHospitals()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllHospitals(hospitals: List<EntityHospital>)


}
