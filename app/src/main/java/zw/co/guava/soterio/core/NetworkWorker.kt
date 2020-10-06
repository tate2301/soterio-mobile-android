package zw.co.guava.soterio.core

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import zw.co.guava.soterio.R
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityExposureKey
import zw.co.guava.soterio.db.repo.RepoExposure

class NetworkWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    override fun doWork(): Result {

        // Indicate whether the work finished successfully with the Result
        return fetchExposureTokens()

    }

    private fun fetchExposureTokens(): Result {
        // Do the work here--in this case, upload the images.

        if(Soterio.isNetworkAvailable(applicationContext)) {
            var success = Result.failure()
            val requestQueue = Volley.newRequestQueue(applicationContext)

            // Tokens request from server
            val tokensRequest = StringRequest(
                Request.Method.GET,
                applicationContext.getString(R.string.server_addr) + applicationContext.getString(R.string.route_tokens),
                {
                    CentralLog.d("ServerAccess", "OnExposureKeyFetchSuccess")


                    // Grab tokens and save them to Database
                    val tokens: List<EntityExposureKey> =
                        Gson().fromJson(it, Array<EntityExposureKey>::class.java).toList()
                    val exposureDao = CoreDatabase.getDatabase(applicationContext).daoExposureKeys()
                    val exposureRepo = RepoExposure(exposureDao)

                    val thread = object : Thread() {
                        override fun run() {
                            super.run()

                            exposureRepo.saveAllTokens(tokens)
                            Soterio.localBroadcastManager!!.sendBroadcast(Intent("EXPOSURE_KEYS_UPDATED"))
                        }
                    }
                    thread.start()

                    success = Result.success()


                },
                {
                    Log.d("ServerAccess", "OnExposureKeyFetchFailure: ${it.message}")
                    success = Result.failure()
                })

            requestQueue.start()

            return success
        } else {
            return Result.failure()
        }

    }
}