package zw.co.guava.soterio.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import pub.devrel.easypermissions.EasyPermissions
import zw.co.guava.soterio.R
import zw.co.guava.soterio.services.ForegroundService
import zw.co.guava.soterio.ui.onboarding.Onboarding
import zw.co.guava.soterio.ui.onboarding.permissions.EnableBluetooth

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    lateinit var lottieViewSonar: LottieAnimationView;
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        mAuth = FirebaseAuth.getInstance()
        lottieViewSonar = findViewById(R.id.sonarAnimationView)
        lottieViewSonar.playAnimation()
    }

    override fun onStart() {
        super.onStart()
        if(/*mAuth.currentUser == null*/true){
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


    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.error))
            .setMessage("Permissions required!")
            .setCancelable(false)
            .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                super.onBackPressed()
                dialog.dismiss()
                finish()

            }
            .show()
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        val foregroundService = Intent(baseContext, ForegroundService::class.java)
        startService(foregroundService)
    }
}