package zw.co.guava.soterio.sync

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseUser
import com.google.gson.Gson
import zw.co.guava.soterio.R
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityHospital
import zw.co.guava.soterio.db.entity.EntityTestingCentre
import zw.co.guava.soterio.db.repo.RepoHospitals
import zw.co.guava.soterio.db.repo.RepoTestingCentres

class ServerSync(val context: Context) {

    private val TAG = "ServerSyncAdapter"

    fun getHospitalsFromServer() {
        val requestQueue = Volley.newRequestQueue(context)

        val hospitalsRequest = StringRequest(
            Request.Method.GET,
            context.getString(R.string.server_addr) + "/hospitals",
            Response.Listener {
                CentralLog.d(TAG, "Event:OnHospitalsFetchSuccess")


                val hospitals: List<EntityHospital> = Gson().fromJson(it, Array<EntityHospital>::class.java).toList()

                val thread = object: Thread() {
                    override fun run() {
                        super.run()
                        val daoHospital = CoreDatabase.getDatabase(context).daoHospitals()

                        RepoHospitals(daoHospital).saveAllHospitals(hospitals)
                    }
                }

                thread.start()

            },
            Response.ErrorListener {
                CentralLog.d(TAG, "Event:Failed to get hospitals because ${it.message}")
            }
        )

        requestQueue.add(hospitalsRequest)
    }

    fun getTestingCentresFromServer() {
        val requestQueue = Volley.newRequestQueue(context)

        val testingCentreRequest = StringRequest(
            Request.Method.GET,
            context.getString(R.string.server_addr) + "/testingcentres",
            {
                CentralLog.d(TAG, "Event:OnTestingCentresFetchSuccess ${it}")

                val testingCentres: List<EntityTestingCentre> = Gson().fromJson(it, Array<EntityTestingCentre>::class.java).toList()

                val thread = object: Thread() {
                    override fun run() {
                        super.run()
                        val daoTestingCentre = CoreDatabase.getDatabase(context).daoTestingCentres()

                        RepoTestingCentres(daoTestingCentre).saveAllTestingCentres(testingCentres)
                    }
                }

                thread.start()

            },
            {
                CentralLog.d(TAG, "Event:Failed to get testing centres because ${it.message}")
            }
        )

        requestQueue.add(testingCentreRequest)
    }

    fun getLatestFeedFromServer() {

    }

    fun getActiveCasesTokensFromServer() {

    }

    fun getNewTokensFromServer(currentUser: FirebaseUser) {

    }

    fun getChangeStreamLogFromServer() {

    }


}