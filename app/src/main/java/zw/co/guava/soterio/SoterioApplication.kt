package zw.co.guava.soterio

import android.app.Application
import android.content.Intent
import androidx.room.Room
import zw.co.guava.soterio.core.classes.SocketAdapter
import zw.co.guava.soterio.core.classes.Utils
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.services.SyncService
import zw.co.guava.soterio.sync.ChangeStreamSync
import zw.co.guava.soterio.sync.ServerSync

class SoterioApplication : Application() {

    companion object {
        var database: CoreDatabase? = null
        var appUtils: Utils? = null
        var socketAdapter: SocketAdapter? = null
        var changeStreamSync: ChangeStreamSync? = null
        var serverSync: ServerSync? = null
    }

    override fun onCreate() {
        super.onCreate()

        socketAdapter = SocketAdapter(applicationContext)
        changeStreamSync = ChangeStreamSync(socketAdapter!!.webSocket)
        changeStreamSync!!.listenForIdentifiers()
        changeStreamSync!!.listenForFeed()

        serverSync = ServerSync(applicationContext)

        appUtils = Utils(applicationContext)
        database = Room.databaseBuilder(this, CoreDatabase::class.java, "local_master")
            .fallbackToDestructiveMigration()
            .build()



        val intent = Intent(this, SyncService::class.java)
        startService(intent)

    }

}