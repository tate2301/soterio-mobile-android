package zw.co.soterio.monitor.ble

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.AdvertiseCallback
import android.bluetooth.le.AdvertiseData
import android.bluetooth.le.AdvertiseSettings
import android.bluetooth.le.BluetoothLeAdvertiser
import android.content.Context
import android.os.ParcelUuid
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import zw.ac.cut.soterio.sble.Constants
import zw.ac.cut.soterio.sble.features.CentralLog

class BluetoothAdvertiser (private val context: Context, val scope: CoroutineScope) {

    private var mAdvertising: Boolean = false
    private val mBluetoothManager : BluetoothManager = context.getSystemService(Service.BLUETOOTH_SERVICE) as BluetoothManager
    private val mBluetoothAdapter: BluetoothAdapter = mBluetoothManager.adapter
    private val mBluetoothAdvertise: BluetoothLeAdvertiser = mBluetoothAdapter.bluetoothLeAdvertiser
    private val TAG = "BluetoothAdvertiser"

    fun startAdvertiser() {
        Log.d("Advertiser","Starting Advertising")
        val gattServer = GattServer(context, scope)
        gattServer.setupServer()


        val advertisingSettings = AdvertiseSettings.Builder()
            .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_POWER)
            .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_LOW)
            .setConnectable(true)
            .setTimeout(0)
            .build()

        val advertiseData = AdvertiseData.Builder()
            .addServiceUuid(ParcelUuid(Constants.SBLE_SERVICE_UUID))
            .setIncludeDeviceName(true)
            .setIncludeTxPowerLevel(true)
            .build()

        mBluetoothAdvertise.startAdvertising(advertisingSettings, advertiseData, advertiseCallback)
        mAdvertising = true

    }

    public fun restartAdvertiser() {
        if(mAdvertising) {
            mBluetoothAdvertise.stopAdvertising(advertiseCallback)
            startAdvertiser()
        }
    }

    private val advertiseCallback: AdvertiseCallback = object: AdvertiseCallback() {
        override fun onStartSuccess(settingsInEffect: AdvertiseSettings?) {
            super.onStartSuccess(settingsInEffect)
            CentralLog.d(TAG, "Advertising has started with $settingsInEffect")
        }

        override fun onStartFailure(errorCode: Int) {
            super.onStartFailure(errorCode)
            CentralLog.d(TAG,"Advertising failed to start. Error Code $errorCode")
        }
    }

}