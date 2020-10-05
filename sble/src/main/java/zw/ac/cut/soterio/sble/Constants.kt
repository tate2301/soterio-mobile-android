package zw.ac.cut.soterio.sble

import androidx.sqlite.db.SimpleSQLiteQuery
import java.util.*

class Constants {

    companion object {
        const val ENInterval: Int = 10
        const val EKRollingPeriod: Int = 144
        const val DEBUG = true
        private const val REQUEST_TURN_DEVICE_LOCATION_ON = 29
        private const val LOCATION_PERMISSION_INDEX = 0
        private const val BACKGROUND_LOCATION_PERMISSION_INDEX = 1
        private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
        private const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
        val SBLE_SERVICE_UUID: UUID = UUID.fromString("00002301-0000-1000-8000-00805f9b34fb")
        val SBLE_IDENTITY_CHARACTERISTIC_UUID: UUID = SBLE_SERVICE_UUID
        const val SERVICE_FOREGROUND_NOTIFICATION_ID = 1023010

    }
}