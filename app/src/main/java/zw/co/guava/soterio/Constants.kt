package zw.co.guava.soterio

import androidx.sqlite.db.SimpleSQLiteQuery
import java.util.*

class Constants {

    companion object {
        const val DEBUG = true
        private const val REQUEST_TURN_DEVICE_LOCATION_ON = 29
        private const val LOCATION_PERMISSION_INDEX = 0
        private const val BACKGROUND_LOCATION_PERMISSION_INDEX = 1
        private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
        private const val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 33
        const val SERVICE_FOREGROUND_NOTIFICATION_ID = 1023010
        const val Radius = 6378.137

        fun getByDistanceRawQuery(lat: Double, lon: Double): SimpleSQLiteQuery {
            return SimpleSQLiteQuery("SELECT *, acos(sin($lat)*sin(radians(latitude)) + cos($lat)*cos(radians(latitude))cos(radians(longitude)-$lon))$Radius AS dist FROM hospitals ORDER BY dist DESC")
        }

    }
}