package zw.co.guava.soterio.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import zw.co.guava.soterio.db.dao.DaoTokens
import zw.co.guava.soterio.db.entity.EntityToken

@Database(entities = arrayOf(EntityToken::class), version = 1, exportSchema = true)
abstract class CoreDatabase: RoomDatabase() {
    abstract fun daoTokens(): DaoTokens

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
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}