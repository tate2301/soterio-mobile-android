package zw.ac.cut.soterio.sble

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import zw.ac.cut.soterio.sble.features.ContactTracingFeature
import zw.ac.cut.soterio.sble.storage.DatabaseManager
import java.time.Instant
import javax.xml.datatype.DatatypeConstants.MINUTES


object Soterio {
    lateinit var context: Context
    fun init(ctx: Context) {
        context = ctx.applicationContext
    }

    fun start() {
        // start the broadcast and scan process
    }

    fun stop() {
        // stop the broadcast and scan process
    }

    fun isEnabled(): Boolean {
        return !BluetoothAdapter.getDefaultAdapter().isEnabled
    }

    fun deviceSupportsLocationlessScanning() {

    }

    fun getVersion() {

    }

    fun getStatus() {

    }

    fun setDiagnosisKeysDataMapping() {

    }

    fun getDiagnosisKeysDataMapping() {

    }

    fun getTemporaryExposureKeyHistory() {

    }

    fun provideDiagnosisKeys() {

    }

    fun getExposureWindows() {

    }

    fun getDailySummaries() {

    }


    val database = Room.databaseBuilder(context, DatabaseManager::class.java, "soterio_exp")
        .fallbackToDestructiveMigration()
        .build()



}