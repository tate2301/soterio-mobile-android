package zw.co.guava.soterio.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import zw.co.guava.soterio.R
import zw.co.guava.soterio.ui.onboarding.Onboarding

<<<<<<< HEAD:app/src/main/java/zw/co/guava/soterio/ui/SplashPage.kt
class SplashPage : AppCompatActivity() {
=======
class SplasActivity : AppCompatActivity() {
>>>>>>> f65c15bcbac15bc7fbe5e42762b18926556f87cb:app/src/main/java/zw/co/guava/soterio/ui/SplasActivity.kt


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object: Thread() {
            override fun run() {
                try
                {
                    Thread.sleep(500)

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