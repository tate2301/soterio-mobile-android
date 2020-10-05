package zw.co.guava.soterio.core.classes

import android.content.Context
import com.github.nkzawa.engineio.client.Socket
import com.github.nkzawa.socketio.client.IO
import zw.ac.cut.soterio.sble.features.CentralLog
import zw.co.guava.soterio.R

class SocketAdapter (val context: Context) {
    private val TAG = "SocketAdapter"
    private lateinit var opts: IO.Options
    val webSocket = getSocket()

    init {
        establishConnection()
        opts = IO.Options()
        opts.reconnection = true
    }

    fun establishConnection(){
        CentralLog.d(TAG, "Event:CONNECT_STARTING")
        val webSocket = getSocket()
        webSocket!!.connect()
            .on(Socket.EVENT_OPEN) {
                CentralLog.d(TAG, "Event:CONNECT_SUCCESS")
            }
            .on(Socket.EVENT_CLOSE) {
                CentralLog.d(TAG, "Event:DISCONNECTED")
            }
            .on(Socket.EVENT_ERROR) {
                CentralLog.d(TAG, "Event:ERROR_CONNECTING")
            }

    }

    private fun getSocket(): com.github.nkzawa.socketio.client.Socket? {
        return IO.socket(context.getString(R.string.server_addr) + "/mobile")
    }

}