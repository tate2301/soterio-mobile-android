package zw.co.guava.soterio.ui.main

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest
import zw.co.guava.soterio.R
import zw.co.guava.soterio.services.ForegroundService
import zw.co.guava.soterio.ui.onboarding.Onboarding
import zw.co.guava.soterio.ui.onboarding.permissions.GetStarted


class MainActivity : AppCompatActivity() {

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


    private val quickPermissionsOption = QuickPermissionsOptions(
        rationaleMessage = "Custom rational message",
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = { req -> rationaleCallback(req) },
        permanentDeniedMethod = { req -> permissionsPermanentlyDenied(req) }
    )

    private fun checkForPermissionsAndStartServices() = runWithPermissions(Manifest.permission.ACCESS_BACKGROUND_LOCATION, options = quickPermissionsOption) {
        val intent = Intent(applicationContext, ForegroundService::class.java)
        startService(intent)
    }

    private fun permissionsPermanentlyDenied(req: QuickPermissionsRequest) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.permissions_required))
            .setMessage(getString(R.string.permissions_location_rationale))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.enable_location)) { dialog, _ ->
                req.openAppSettings()
                dialog.dismiss()
            }
            .show()
    }

    private fun rationaleCallback(req: QuickPermissionsRequest) {
        MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.permissions_required))
            .setMessage(getString(R.string.permissions_location_rationale))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.enable_location)) { dialog, _ ->
                req.openAppSettings()
                dialog.dismiss()
            }
            .show()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}