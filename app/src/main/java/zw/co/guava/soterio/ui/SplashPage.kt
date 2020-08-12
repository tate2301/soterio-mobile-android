package zw.co.guava.soterio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import zw.co.guava.soterio.R
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityHospital
import zw.co.guava.soterio.db.repo.RepoHospitals
import zw.co.guava.soterio.ui.main.MainActivity
import zw.co.guava.soterio.ui.main.getinfo.exposure.ExposureNotifications
import zw.co.guava.soterio.ui.main.getinfo.testing.TestingCenters

class SplashPage : AppCompatActivity() {

    private var scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /*scope.launch {
            val hospital = EntityHospital(
                name = "Chinhoyi Provincial Hospital",
                address = "Chinhoyi",
                latitude = 1.0983,
                longitude = 1.0989474,
                phone = "+263789494393"
            )
            val l = listOf<EntityHospital>(hospital)
            val thread = object: Thread() {
                override fun run() {
                    super.run()
                    val daoHospital = CoreDatabase.getDatabase(applicationContext).daoHospitals()

                    RepoHospitals(daoHospital).saveAllHospitals(l)
                }
            }

            thread.start()



        }*/

        val background = object: Thread() {
            override fun run() {
                try
                {
                    Thread.sleep(500)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }catch (e: Exception)
                {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }
}