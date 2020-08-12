package zw.co.guava.soterio.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import zw.co.guava.soterio.db.dao.DaoEncounter
import zw.co.guava.soterio.db.dao.DaoHospitals
import zw.co.guava.soterio.db.dao.DaoTestingCentres
import zw.co.guava.soterio.db.dao.DaoTokens
import zw.co.guava.soterio.db.entity.EntityEncounter
import zw.co.guava.soterio.db.entity.EntityHospital
import zw.co.guava.soterio.db.entity.EntityTestingCentre
import zw.co.guava.soterio.db.entity.EntityToken

@Database(entities = [EntityToken::class, EntityEncounter::class, EntityHospital::class, EntityTestingCentre::class], version = 3, exportSchema = true)
abstract class CoreDatabase: RoomDatabase() {
    abstract fun daoTokens(): DaoTokens
    abstract fun daoEncounter(): DaoEncounter
    abstract fun daoHospitals(): DaoHospitals
    abstract fun daoTestingCentres(): DaoTestingCentres

    companion object {
        @Volatile
        private var INSTANCE: CoreDatabase? = null

        fun getDatabase(context: Context): CoreDatabase {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoreDatabase::class.java,
                    "soterio_db"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}