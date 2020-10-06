package zw.co.guava.soterio.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import zw.co.guava.soterio.db.dao.*
import zw.co.guava.soterio.db.dao.DaoCase
import zw.co.guava.soterio.db.entity.*

@Database(entities = [
    EntityToken::class,
    EntityEncounter::class,
    EntityHospital::class,
    EntityTestingCentre::class,
    EntityCase::class,
    EntityFeed::class,
    EntityExposureKey::class],

    version = 7, exportSchema = true)
abstract class CoreDatabase: RoomDatabase() {
    abstract fun daoTokens(): DaoTokens
    abstract fun daoEncounter(): DaoEncounter
    abstract fun daoHospitals(): DaoHospitals
    abstract fun daoTestingCentres(): DaoTestingCentres
    abstract fun daoCases(): DaoCase
    abstract fun daoFeed(): DaoFeed
    abstract fun daoExposureKeys(): DaoExposureKeys

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