package zw.co.guava.soterio.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import zw.co.guava.soterio.Constants
import zw.co.guava.soterio.R
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.ble.RxBleScanner
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.core.classes.NotificationsTemplates
import zw.co.guava.soterio.core.classes.Utils
import zw.co.soterio.monitor.ble.BluetoothAdvertiser

class ForegroundService : Service() {

    private val scope: CoroutineScope = MainScope()
    private val TAG = "ForegroundService"

    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        // Check if multi-advertisement is supported and start the advertiser
        if(!BluetoothAdapter.getDefaultAdapter().isEnabled) {
            BluetoothAdapter.getDefaultAdapter().enable()
        }

        if(BluetoothAdapter.getDefaultAdapter().isMultipleAdvertisementSupported) {
            val bleAdvertiser = BluetoothAdvertiser(applicationContext, scope)
            bleAdvertiser.startAdvertiser()
            CentralLog.d(TAG,"Multi-Advertisement supported. Launching Advertiser")
        }

        val bluetoothScanner = RxBleScanner(applicationContext, scope)
        bluetoothScanner.start()

        createNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY

    }


    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "zw.ac.cut.soterio.default_notification"
            val descriptionText = "Default Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("2301001", name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = applicationContext?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val builder = applicationContext?.let {
            NotificationCompat.Builder(it, "2301001")
                .setSmallIcon(R.drawable.notification)
                .setColor(Color.BLUE)
                .setStyle(NotificationCompat.BigTextStyle().bigText(getText(R.string.service_not_ok_body)))
                .setContentTitle(getString(R.string.service_ok_title))
                .setContentText(getString(R.string.service_ok_body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        }

        with(applicationContext?.let { NotificationManagerCompat.from(it) }) {
            builder?.build()?.let {
                startForeground(2301001, it)
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        CentralLog.i(TAG, "BluetoothMonitoringService destroyed - tearing down")

        CentralLog.i(TAG, "BluetoothMonitoringService destroyed")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}