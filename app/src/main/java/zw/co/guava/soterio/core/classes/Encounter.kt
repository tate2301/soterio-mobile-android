package zw.co.guava.soterio.core.classes

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Encounter (
    val identifier: String,
    val rss: Int,
    val timestamp: Long,
    val txPower: Int
) {
    fun getWriteRequestPayload(): ByteArray {
        return gson.toJson(this).toString().toByteArray(Charsets.UTF_8)
    }

    companion object {
        val gson: Gson = GsonBuilder().disableHtmlEscaping().create()

        fun createWriteRequestPayload(dataBytes: ByteArray) : Encounter {
            val dataString = String(dataBytes, Charsets.UTF_8)
            return gson.fromJson(dataString, Encounter::class.java)
        }
    }
}