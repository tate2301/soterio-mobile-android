package zw.co.guava.soterio.sync

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import zw.ac.cut.soterio.sble.features.CentralLog


class DBSyncWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
    override fun doWork(): Result {
        return try {
            Result.success()
        } catch (throwable: Throwable) {
            CentralLog.e("WorkManager", "${throwable.message}")
            Result.failure()
        }
    }
}