package zw.co.guava.soterio.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import zw.co.guava.soterio.Soterio

class ContactTracingStateReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Soterio.toggleContactTracingStatus(context!!)
    }
}