package zw.co.guava.soterio.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_landing_page.*
import zw.co.guava.soterio.R
import zw.co.guava.soterio.services.ForegroundService
import zw.co.guava.soterio.ui.main.feed.FeedFragment
import zw.co.guava.soterio.ui.main.home.HomeFragment
import zw.co.guava.soterio.ui.onboarding.Onboarding

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        mAuth = FirebaseAuth.getInstance()

        val homeFragment = HomeFragment()
        val feedFragment = FeedFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayoutView, homeFragment)
            commit()
        }

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayoutView, homeFragment)
                        commit()
                    }
                    true
                }
                R.id.feed -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.frameLayoutView, feedFragment)
                        commit()
                    }
                    true
                }

                else -> {
                    false
                }
            }
        }


    }

    override fun onStart() {
        super.onStart()
        if(mAuth.currentUser == null){
            val intent = Intent(baseContext, Onboarding::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            finish()
        } else {
            checkForPermissionsAndStartServices()
        }
    }
















    private fun checkForPermissionsAndStartServices() {

        val foregroundService = Intent(baseContext, ForegroundService::class.java)
        startService(foregroundService)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitFromApp()
    }

    private fun exitFromApp() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        startActivity(intent)
    }

}