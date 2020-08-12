package zw.co.guava.soterio.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.sync.DBSyncWorker
import java.util.concurrent.TimeUnit

class SyncService : Service() {
    private lateinit var workManager: WorkManager

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        workManager = WorkManager.getInstance()

        val work = PeriodicWorkRequestBuilder<DBSyncWorker>(12, TimeUnit.HOURS)
            .build()
        workManager.enqueueUniquePeriodicWork("DBSyncWorker", ExistingPeriodicWorkPolicy.REPLACE, work)

        CentralLog.d("Event:DBSyncWorker", "Enqueueing worker now")
    }
}
