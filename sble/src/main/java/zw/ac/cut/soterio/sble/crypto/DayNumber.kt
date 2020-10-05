package zw.ac.cut.soterio.sble.crypto

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.common.base.Preconditions
import java.nio.ByteBuffer
import java.time.Instant
import java.util.concurrent.TimeUnit


/**
 * A day number is also known as exposure key rolling period number or EKRollingPeriod number. We
 * call it day number internally because the exposure key rolling period is 144 * 10 minutes or 24
 * hours. Each day number represents a 24-hour window that is based on Unix Epoch Time and is
 * timezone independent.
 *
 *
 * The value shall be in [0, 65535] interval. Though 18366 is the smallest legitimate day number
 * as of 2020/04/14, we allow the day number to be smaller than that to properly handle wrong system
 * clock on some devices.
 */
class DayNumber {
    val value: Int

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(instant: Instant) {
        value = getDayNumber(instant)
    }

    constructor(value: Int) {
        this.value = value
    }

    /**
     * Puts the 2 bytes (big-endian) representation of this [DayNumber] in a [ByteBuffer].
     * The 2 bytes can represent a day number till 2149/06/07.
     *
     * @param byteBuffer the destination buffer to [ByteBuffer.put] the byte representation.
     */
    fun putIn(byteBuffer: ByteBuffer) {
        putIn(value, byteBuffer)
    }

    companion object {
        val MIN_DAY_NUMBER = DayNumber(0)
        val MAX_DAY_NUMBER = DayNumber(0xFFFF)
        const val sizeBytes = 2

        /** The static version of [.putIn].  */
        fun putIn(dayNumber: Int, byteBuffer: ByteBuffer) {
            Preconditions.checkArgument(byteBuffer.remaining() >= sizeBytes)
            byteBuffer.putShort(dayNumber.toShort())
        }

        /**
         * Gets a [DayNumber] from the 2 bytes (big-endian) representation in a [ByteBuffer].
         */
        fun getFrom(byteBuffer: ByteBuffer): DayNumber {
            return DayNumber(getValueFrom(byteBuffer))
        }

        /** The static version of [.getFrom].  */
        fun getValueFrom(byteBuffer: ByteBuffer): Int {
            Preconditions.checkArgument(byteBuffer.remaining() >= sizeBytes)
            return byteBuffer.short.toInt() and 0xFFFF // Equivalent of Java 8 Short.toUnsignedInt().
        }

        /**
         * Returns the int representation of a day number of an [Instant].
         *
         * @param instant the instant to get day number of
         * @return day number with the range in [0, 65535] interval, or undefined if the `instant`
         * is later than June 6, 2149 12:00:00 AM GMT.
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun getDayNumber(instant: Instant): Int {
            return (instant.toEpochMilli() / TimeUnit.DAYS.toMillis(1)).toInt()
        }
    }
}