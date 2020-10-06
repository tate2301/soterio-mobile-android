package zw.co.guava.soterio

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.room.Room
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import zw.co.guava.soterio.core.NetworkWorker
import zw.co.guava.soterio.core.classes.SocketAdapter
import zw.co.guava.soterio.core.classes.Utils
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.receivers.ContactTracingStateReceiver
import zw.co.guava.soterio.receivers.ExposureKeyMatchedReceiver
import zw.co.guava.soterio.receivers.ExposureKeyNoneMatched
import zw.co.guava.soterio.receivers.ExposureUpdateReceiver
import zw.co.guava.soterio.services.SyncService
import zw.co.guava.soterio.sync.ChangeStreamSync
import zw.co.guava.soterio.sync.ServerSync
import java.util.concurrent.TimeUnit


class Soterio : Application() {

    companion object {
        var database: CoreDatabase? = null
        var appUtils: Utils? = null
        var socketAdapter: SocketAdapter? = null
        var changeStreamSync: ChangeStreamSync? = null
        var serverSync: ServerSync? = null
        var localBroadcastManager: LocalBroadcastManager? = null
        val EXPOSURE_KEYS_UPDATED_FILTER = IntentFilter("zw.co.guava.soterio.EXPOSURE_KEYS_UPDATED")
        val EXPOSURE_KEYS_MATCHED_FILTER = IntentFilter("zw.co.guava.soterio.EXPOSURE_KEYS_MATCHED")
        val EXPOSURE_KEYS_NONE_MATCHED_FILTER = IntentFilter("zw.co.guava.soterio.EXPOSURE_KEYS_NONE_MATCHED")
        val CONTACT_TRACING_STATE_CHANGED_FILTER = IntentFilter("zw.co.guava.soterio.CONTACT_TRACING_STATE_CHANGED")
        const val CONTACT_TRACING_KEY = "CONTACT_TRACING_KEY"
        val exposureUpdatedReceiver = ExposureUpdateReceiver()
        val exposureKeyMatchedReceiver = ExposureKeyMatchedReceiver()
        val exposureKeyNoneMatched = ExposureKeyNoneMatched()
        val contactTracingStateReceiver = ContactTracingStateReceiver()

        fun isNetworkAvailable(context: Context?): Boolean {
            val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }

        fun sharedPrefs(context: Context): SharedPreferences? {
            return context.getSharedPreferences(
                "zw.co.guava.soterio", Context.MODE_PRIVATE
            )
        }

        fun isContactTracingEnabled(context: Context): Boolean {
            return sharedPrefs(context)!!.getBoolean(CONTACT_TRACING_KEY, true)
        }

        fun toggleContactTracingStatus(context: Context) {
            sharedPrefs(context)!!.edit().putBoolean(CONTACT_TRACING_KEY, !isContactTracingEnabled(context)).apply()
        }

    }



    override fun onCreate() {
        super.onCreate()

        val networkWorker: WorkRequest = PeriodicWorkRequestBuilder<NetworkWorker>(24, TimeUnit.HOURS).build()
        WorkManager.getInstance().enqueue(networkWorker)

        localBroadcastManager = LocalBroadcastManager.getInstance(applicationContext)
        localBroadcastManager!!.registerReceiver(exposureUpdatedReceiver, EXPOSURE_KEYS_UPDATED_FILTER)
        localBroadcastManager!!.registerReceiver(exposureKeyMatchedReceiver, EXPOSURE_KEYS_MATCHED_FILTER)
        localBroadcastManager!!.registerReceiver(exposureKeyNoneMatched, EXPOSURE_KEYS_NONE_MATCHED_FILTER)
        localBroadcastManager!!.registerReceiver(
            contactTracingStateReceiver,
            CONTACT_TRACING_STATE_CHANGED_FILTER
        )

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