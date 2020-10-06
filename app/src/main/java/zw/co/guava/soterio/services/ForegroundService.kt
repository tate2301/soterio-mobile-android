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
import kotlinx.coroutines.MainScope
import zw.co.guava.soterio.R
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.ble.RxBleScanner
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.soterio.monitor.ble.BluetoothAdvertiser

class ForegroundService : Service() {

    private val scope: CoroutineScope = MainScope()
    private val TAG = "ForegroundService"
    lateinit var bluetoothScanner: RxBleScanner
    lateinit var bleAdvertiser: BluetoothAdvertiser



    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        val BluetoothReceiver = object: BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent!!.action

                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    when(intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)) {
                        BluetoothAdapter.STATE_OFF -> {
                            CentralLog.d(TAG, "Contact Tracing Service not running")
                            createLackingThingsNotification()
                        }

                        BluetoothAdapter.STATE_TURNING_OFF -> {
                            tearDown()
                        }

                        BluetoothAdapter.STATE_TURNING_ON -> {
                            spinUp()
                        }

                        BluetoothAdapter.STATE_ON -> {
                            CentralLog.d(TAG, "Contact Tracing Service running")
                        }
                    }
                }

            }
        }

        val contactTracingActivationReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent!!.action

                if(action!! == "zw.co.guava.soterio.CONTACT_TRACING_STATE_CHANGED") {
                    if(Soterio.isContactTracingEnabled(applicationContext)) {
                        spinUp()
                    }  else {
                        createLackingThingsNotification()
                    }
                }
            }
        }
        registerReceiver(contactTracingActivationReceiver, Soterio.CONTACT_TRACING_STATE_CHANGED_FILTER)

        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(BluetoothReceiver, filter)

        spinUp()
    }

    @InternalCoroutinesApi
    private fun spinUp() {
        // Check if multi-advertisement is supported and start the advertiser
        if(Soterio.isContactTracingEnabled(applicationContext)) {
            if(!BluetoothAdapter.getDefaultAdapter().isEnabled) {
                BluetoothAdapter.getDefaultAdapter().enable()
            }

            if(BluetoothAdapter.getDefaultAdapter().isMultipleAdvertisementSupported) {
                bleAdvertiser = BluetoothAdvertiser(applicationContext, scope)
                bleAdvertiser.startAdvertiser()
                CentralLog.d(TAG,"Multi-Advertisement supported. Launching Advertiser")
            }

            bluetoothScanner = RxBleScanner(applicationContext, scope)
            bluetoothScanner.start()
            createRunningNotification()

        } else {
            CentralLog.d(TAG,"Contact Tracing Feature not enabled")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY

    }


    private fun createRunningNotification() {
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
                .setSmallIcon(R.drawable.ic_soterio)
                .setColor(Color.BLUE)
                .setStyle(NotificationCompat.BigTextStyle().bigText(getString(R.string.service_ok_body)))
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

    private fun createLackingThingsNotification() {
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
                .setSmallIcon(R.drawable.ic_soterio)
                .setColor(Color.BLUE)
                .setStyle(NotificationCompat.BigTextStyle().bigText(getString(R.string.service_not_ok_body)))
                .setContentTitle(getString(R.string.service_not_ok))
                .setContentText(getString(R.string.service_not_ok_body))
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
        tearDown()
    }

    private fun tearDown() {
        CentralLog.i(TAG, "BluetoothMonitoringService destroyed - tearing down")
        bluetoothScanner.stop()
        bleAdvertiser.stopAdvertiser()
        CentralLog.i(TAG, "BluetoothMonitoringService destroyed")
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}