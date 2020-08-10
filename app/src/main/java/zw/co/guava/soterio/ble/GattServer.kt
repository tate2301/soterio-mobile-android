package zw.co.soterio.monitor.ble

import android.app.Service
import android.bluetooth.*
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import zw.co.guava.soterio.Constants
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.core.classes.StreetPassStorage
import zw.co.guava.soterio.core.classes.Utils
import java.util.*
import kotlin.properties.Delegates


class GattServer (private val context: Context, val scope: CoroutineScope) {
    private val TAG = "GattServer"

    private val mBluetoothManager:BluetoothManager = context.getSystemService(Service.BLUETOOTH_SERVICE) as BluetoothManager
    private lateinit var mGattServer: BluetoothGattServer
    val gson: Gson = GsonBuilder().disableHtmlEscaping().create()
    private var serviceUUID: UUID by Delegates.notNull()


    init {
        this.serviceUUID = Constants.MONITOR_SERVICE_UUID
    }

     fun setupServer() {
         mGattServer = mBluetoothManager.openGattServer(context, gattServerCallback)
         Log.d("GattServer", "GattServer has been launched")

         val service = BluetoothGattService(Constants.MONITOR_SERVICE_UUID, BluetoothGattService.SERVICE_TYPE_PRIMARY)

         val identityCharacteristic = BluetoothGattCharacteristic(
             Constants.MONITOR_SERVICE_UUID,
             BluetoothGattCharacteristic.PROPERTY_READ or BluetoothGattCharacteristic.PROPERTY_WRITE,
             BluetoothGattCharacteristic.PERMISSION_READ or BluetoothGattCharacteristic.PERMISSION_WRITE
         )
         service.addCharacteristic(identityCharacteristic)
         mGattServer.addService(service)

         Log.d("GattServer", "Launching Gatt Server")
    }

    private val gattServerCallback = object: BluetoothGattServerCallback() {
        val writeDataPayload: MutableMap<String, ByteArray> = HashMap()
        val readPayloadMap: MutableMap<String, ByteArray> = HashMap()

        override fun onConnectionStateChange(device: BluetoothDevice, status: Int, newState: Int) {
            super.onConnectionStateChange(device, status, newState)
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    CentralLog.i(TAG, "${device.address} Connected to local GATT server")
                    device.let {
                        val b = mBluetoothManager.getConnectedDevices(BluetoothProfile.GATT).contains(device)
                    }
                }

                BluetoothProfile.STATE_DISCONNECTED -> {
                    CentralLog.i(TAG, "${device.address} Disconnected from local GATT server.")
                    readPayloadMap.remove(device.address)
                    device.let {
                        Utils.broadcastDeviceConnected(context, device)
                    }

                }

                else -> {
                    CentralLog.i(TAG, "Connection status: $newState - ${device.address}")
                }
            }
        }

        override fun onServiceAdded(status: Int, service: BluetoothGattService?) {
            super.onServiceAdded(status, service)
            Log.d("GattServer", "Added service to gatt $service")

        }

        override fun onCharacteristicReadRequest(device: BluetoothDevice?, requestId: Int, offset: Int, characteristic: BluetoothGattCharacteristic?) {
            super.onCharacteristicReadRequest(device, requestId, offset, characteristic)

            scope.launch {
                val streetPassStorage = StreetPassStorage(context)
                val identifier = streetPassStorage.getActiveToken().Identifier

                mGattServer.sendResponse(device, requestId, 0, offset, identifier.toByteArray())

            }

        }

        override fun onExecuteWrite(device: BluetoothDevice, requestId: Int, execute: Boolean) {
            super.onExecuteWrite(device, requestId, execute)
            val data = writeDataPayload[device.address]
            CentralLog.i(TAG, "onExecuteWrite - $requestId- ${device.address} -")

            data.let { dataBuffer ->
                if (dataBuffer != null) {
                    CentralLog.i(TAG, "onExecuteWrite - $requestId- ${device.address} - ${String(dataBuffer, Charsets.UTF_8)}")

                    mGattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, 0, null)

                } else {
                    mGattServer?.sendResponse(device, requestId, BluetoothGatt.GATT_FAILURE, 0, null)
                }
            }
        }

        override fun onCharacteristicWriteRequest(device: BluetoothDevice?, requestId: Int, characteristic: BluetoothGattCharacteristic, preparedWrite: Boolean, responseNeeded: Boolean, offset: Int, value: ByteArray?) {
            CentralLog.i(TAG, "onCharacteristicWriteRequest - - preparedWrite: $preparedWrite")
            CentralLog.i(TAG, "onCharacteristicWriteRequest from - $requestId - $offset with data ${value.toString()}")

            device?.let {
                CentralLog.i(TAG, "onCharacteristicWriteRequest - ${device.address} - preparedWrite: $preparedWrite")
                CentralLog.i(TAG, "onCharacteristicWriteRequest from ${device.address} - $requestId - $offset with data ${value.toString()}")

            }
            if (device == null) {
                CentralLog.e(TAG, "Write stopped - no device")
            }
        }


    }




}