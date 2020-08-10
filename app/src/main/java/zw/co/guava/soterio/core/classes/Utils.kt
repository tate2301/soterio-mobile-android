package zw.co.guava.soterio.core.classes

import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import zw.co.guava.soterio.services.ForegroundService

class Utils (context: Context){

    private val sharedPref: SharedPreferences = context.getSharedPreferences("SoterioUtils", Context.MODE_PRIVATE)

    fun startBluetoothMonitoringService(context: Context) {
        val intent = Intent(context, ForegroundService::class.java)
        context.startService(intent)
    }

    companion object {
        fun broadcastDeviceConnected(context: Context, device: BluetoothDevice?) {

        }

    }


}