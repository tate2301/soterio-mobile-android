package zw.co.guava.soterio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.auth.VerifyAddress
import zw.co.guava.soterio.ui.auth.VerifyOtp
import zw.co.guava.soterio.ui.auth.VerifyPersonalDetails
import zw.co.guava.soterio.ui.auth.VerifyPhone
import zw.co.guava.soterio.ui.onboarding.FirstFragment
import zw.co.guava.soterio.ui.onboarding.Onboarding
import zw.co.guava.soterio.ui.permissions.enable_bluetooth

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val background = object: Thread() {
            override fun run() {
                try
                {
                    Thread.sleep(3000)

                    val intent = Intent(baseContext, Onboarding::class.java)
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