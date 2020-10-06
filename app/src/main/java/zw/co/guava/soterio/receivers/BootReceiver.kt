package zw.co.guava.soterio.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.services.ForegroundService

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        CentralLog.d("SOTERIO", "Received boot complete")
        val intent = Intent(context, ForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intent)
        }
        else {
            context!!.startService(intent);
        }
    }
}