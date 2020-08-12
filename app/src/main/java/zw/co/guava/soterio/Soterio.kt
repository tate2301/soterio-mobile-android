package zw.co.guava.soterio

import android.app.Application
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import zw.co.guava.soterio.core.classes.Utils
import zw.co.guava.soterio.db.CoreDatabase

class Soterio : Application() {

    companion object {
        var database: CoreDatabase? = null
        var appUtils: Utils? = null
    }

    override fun onCreate() {
        super.onCreate()

        appUtils = Utils(applicationContext)
        database = Room.databaseBuilder(this, CoreDatabase::class.java, "local_master")
            .fallbackToDestructiveMigration()
            .build()


    }

}