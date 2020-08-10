package zw.co.guava.soterio.ble

import android.content.Context
import android.os.ParcelUuid
import android.util.Log
import androidx.room.RoomDatabase
import com.polidea.rxandroidble2.LogConstants
import com.polidea.rxandroidble2.LogOptions
import com.polidea.rxandroidble2.RxBleClient
import com.polidea.rxandroidble2.RxBleConnection
import com.polidea.rxandroidble2.scan.ScanFilter
import com.polidea.rxandroidble2.scan.ScanResult
import com.polidea.rxandroidble2.scan.ScanSettings
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.coroutines.*
import zw.co.guava.soterio.Constants
import zw.co.guava.soterio.Soterio
import zw.co.guava.soterio.core.classes.CentralLog
import zw.co.guava.soterio.core.classes.StreetPassStorage
import zw.co.guava.soterio.db.entity.EntityEncounter
import java.util.HashMap


class RxBleScanner(val context: Context, private val scope: CoroutineScope) {

    private val rxBleClient = RxBleClient.create(context)
    private var devices: MutableList<Pair<ScanResult, Int>> = mutableListOf()
    private val connections: MutableList<Disposable?> = mutableListOf()
    private lateinit var appDatabase: RoomDatabase
    private val TAG = "RxBleScanner"

    private val monitorFilter = ScanFilter.Builder()
        .setServiceUuid(ParcelUuid(Constants.MONITOR_SERVICE_UUID))
        .build()

    private var scanDisposable: Disposable? = null
    private var scanJob: Job? = null



    /*
     When iPhone goes into the background iOS changes how services are advertised:

       1) The service uuid is now null
       2) The information to identify the service is encoded into the manufacturing data in a
       unspecified/undocumented way.

     */


    private val settings = ScanSettings.Builder()
        .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
        .build()

    @InternalCoroutinesApi
    fun start() {
        appDatabase = Soterio.database!!
        Log.d("RxBle", "Starting Scan")
        val logOptions = LogOptions.Builder()
            .setLogLevel(LogConstants.DEBUG)
            .setLogger { level, tag, msg -> CentralLog.d(tag, msg) }
            .build()
        RxBleClient.updateLogOptions(logOptions)
        scanJob = scope.launch {
            while (isActive) {
                CentralLog.d(TAG,"scan - Starting")
                scanDisposable = scan()
                var attempts = 0
                while (attempts++ < 10 && devices.isEmpty()) {
                    if (!isActive) return@launch
                    delay(5000)
                }
                CentralLog.d(TAG,"scan - Stopping")
                if (!isActive) return@launch

                scanDisposable?.dispose()
                scanDisposable = null

                // Some devices are unable to connect while a scan is running
                // or just after it finished
                delay(1_000)

                devices.distinctBy { it.first.bleDevice }.map {
                    CentralLog.d(TAG,"scan - Connecting to $it")
                    connectToDevice(it.first, it.second, scope)
                }

                connections.map { it?.dispose() }
                connections.clear()
                devices.clear()
            }
        }
    }

    private fun scan(): Disposable? = rxBleClient
            .scanBleDevices(
                settings,
                monitorFilter
            )
            .subscribe(
                {
                    Log.d("RxBle", "Scan found = ${it.bleDevice}")
                    devices.add(Pair(it, it.scanRecord.txPowerLevel))
                },
                ::onConnectionError
            )

    fun stop() {
        scanDisposable?.dispose()
        scanDisposable = null
        scanJob?.cancel()
    }

    private fun connectToDevice(scanResult: ScanResult, txPowerAdvertised: Int, coroutineScope: CoroutineScope) {
        val macAddress = scanResult.bleDevice.macAddress
        Log.d("RxBle", "Found $macAddress mmeta ${scanResult.scanRecord.serviceData}")
        CentralLog.d(TAG,"Connecting to $macAddress")
        scanResult
            .bleDevice
            .establishConnection(false)
            .flatMapSingle { connection ->
                negotiateMTU(connection)
            }
            .flatMapSingle { connection ->

                // Read data here
                read(connection, txPowerAdvertised, coroutineScope)
                    .doOnSuccess {

                        // Write data here
                        write(connection)

                    }
            }
            .doOnSubscribe {
                connections.add(it)
            }
            .take(1)
            .blockingSubscribe(
                { event ->
                    storeEvent(event)
                },
                { e ->
                    CentralLog.e(TAG,"failed reading from $macAddress - $e")
                }
            )
    }

    private fun negotiateMTU(connection: RxBleConnection): Single<RxBleConnection> {
        // the overhead appears to be 2 bytes
        return connection.requestMtu(2 + 410)
            .doOnSubscribe { CentralLog.i(TAG,"Negotiating MTU started") }
            .doOnError { e: Throwable? ->
                CentralLog.e(TAG,"Failed to negotiate MTU: $e")
                Observable.error<Throwable?>(e)
            }
            .doOnSuccess { CentralLog.i(TAG,"Negotiated MTU: $it") }
            .ignoreElement()
            .andThen(Single.just(connection))
    }

    private fun read(connection: RxBleConnection, txPower: Int, scope: CoroutineScope): Single<Event> = Single.zip(
            connection.readCharacteristic(Constants.MONITOR_SERVICE_UUID),
            connection.readRssi(),
            BiFunction<ByteArray, Int, Event> { characteristicValue, rssi ->
                Event(characteristicValue, rssi, txPower, scope, System.currentTimeMillis())
            }
        )

    private fun write(connection: RxBleConnection) {
        val writeDataPayload: MutableMap<String, ByteArray> = HashMap()
        val streetPassStorage = StreetPassStorage(context)

        scope.launch {
            val identifier = streetPassStorage.getActiveToken().Identifier
            writeDataPayload["identifier"] = identifier.toByteArray()
            writeDataPayload["rssi"] = connection.readRssi().toString().toByteArray()
            CentralLog.d(TAG,"Event:WritingRequest $identifier")

            connection.writeCharacteristic(Constants.MONITOR_SERVICE_UUID, writeDataPayload.toString().toByteArray())
        }
    }

    private fun onConnectionError(e: Throwable) {
        CentralLog.d(TAG,"Connection failed with: $e")
    }

    private fun storeEvent(event: Event) {
        CentralLog.d(TAG,"Event ${String(event.identifier)}: $event")
        createOrUpdateContactEvent(
            event.identifier,
            event.txPower,
            event.timestamp,
            event.txPower
        )
    }

    private fun createOrUpdateContactEvent(identifier: ByteArray, rssi: Int, timestamp: Long, txPower: Int) {
        val deviceIdentifier = String(identifier)
        CentralLog.d(TAG, "readSuccess: ${deviceIdentifier}")

    }

    private fun saveEncounter(encounter: EntityEncounter) {
        val thread: Thread = object : Thread() {
            override fun run() {

            }
        }
        thread.start()
    }

    @Suppress("ArrayInDataClass")
    private data class Event(
        val identifier: ByteArray,
        val rssi: Int,
        val txPower: Int,
        val scope: CoroutineScope,
        val timestamp: Long
    )


}

