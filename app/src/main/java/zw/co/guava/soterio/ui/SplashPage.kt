package zw.co.guava.soterio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.main.MainActivity
import zw.co.guava.soterio.ui.main.getinfo.Hospitals
import zw.co.guava.soterio.ui.onboarding.Onboarding

class SplashPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object: Thread() {
            override fun run() {
                try
                {
                    Thread.sleep(500)

                    val intent = Intent(baseContext, Hospitals::class.java)
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