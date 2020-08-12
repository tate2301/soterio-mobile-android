package zw.co.guava.soterio.ui.onboarding.permissions

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.livinglifetechway.quickpermissions_kotlin.runWithPermissions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsOptions
import com.livinglifetechway.quickpermissions_kotlin.util.QuickPermissionsRequest
import kotlinx.android.synthetic.main.activity_enable_bluetooth.*
import zw.co.guava.soterio.R


class EnableBluetooth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enable_bluetooth)

        bluetoothButton.setOnClickListener {
            requestBluetoothPermissions()
        }
    }

    private val quickPermissionsOption = QuickPermissionsOptions(
        rationaleMessage = "Custom rational message",
        permanentlyDeniedMessage = "Custom permanently denied message",
        rationaleMethod = { req -> rationaleCallback(req) },
        permanentDeniedMethod = { req -> permissionsPermanentlyDenied(req) }
    )

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

    private fun requestBluetoothPermissions() = runWithPermissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.BLUETOOTH, options = quickPermissionsOption) {
        val intent = Intent(baseContext, GetStarted::class.java)
        startActivity(intent)
    }

}