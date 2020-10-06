package zw.co.guava.soterio.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import zw.co.guava.soterio.core.classes.CentralLog

class ExposureUpdateReceiver: BroadcastReceiver() {
    val EXPOSURE_RECEIVER = "EXPOSURE_RECEIVER"

    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent!!.action == "EXPOSURE_KEYS_UPDATED") {
            CentralLog.d(EXPOSURE_RECEIVER, "KEYS_UPDATED")
        }

    }
}