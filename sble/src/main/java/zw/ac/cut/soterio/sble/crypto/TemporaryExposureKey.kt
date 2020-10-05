package zw.ac.cut.soterio.sble.crypto

import android.os.Parcel
import android.os.Parcelable
import com.google.common.base.Preconditions
import java.math.BigInteger
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * A key generated for advertising over a window of time.
 */
class TemporaryExposureKey : Parcelable {
    var keyData: ByteArray

    /**
     * A number describing when a key starts. It is equal to startTimeOfKeySinceEpochInSecs / (60 *
     * 10).
     */
    var rollingStartIntervalNumber: Int

    /**
     * Risk of transmission associated with the person this key came from.
     */
    @get:RiskLevel
    @RiskLevel
    var transmissionRiskLevel: Int

    /**
     * A number describing how long a key is valid. It is expressed in increments of 10 minutes (e.g.
     * 144 for 24 hours).
     */
    var rollingPeriod: Int

    internal constructor(
        keyData: ByteArray,
        rollingStartIntervalNumber: Int,
        @RiskLevel transmissionRiskLevel: Int,
        rollingPeriod: Int
    ) {
        this.keyData = keyData
        this.rollingStartIntervalNumber = rollingStartIntervalNumber
        this.transmissionRiskLevel = transmissionRiskLevel
        this.rollingPeriod = rollingPeriod
    }

    private constructor(`in`: Parcel) {
        val keyLen = `in`.readInt()
        keyData = ByteArray(keyLen)
        `in`.readByteArray(keyData)
        rollingStartIntervalNumber = `in`.readInt()
        transmissionRiskLevel = `in`.readInt()
        rollingPeriod = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(keyData.size)
        dest.writeByteArray(keyData)
        dest.writeInt(rollingStartIntervalNumber)
        dest.writeInt(transmissionRiskLevel)
        dest.writeInt(rollingPeriod)
    }

    override fun describeContents(): Int {
        return 0
    }

    /**
     * The randomly generated Temporary Exposure Key information.
     */
    @JvmName("getKeyData1")
    fun getKeyData(): ByteArray {
        return Arrays.copyOf(keyData, keyData.size)
    }

    override fun equals(obj: Any?): Boolean {
        if (obj is TemporaryExposureKey) {
            val that = obj
            return (Arrays.equals(keyData, that.getKeyData())
                    && (rollingStartIntervalNumber == that.rollingStartIntervalNumber)
                    && (transmissionRiskLevel == that.transmissionRiskLevel)
                    && (rollingPeriod == that.rollingPeriod))
        }
        return false
    }

    override fun hashCode(): Int {
        return Objects.hash(
            keyData, rollingStartIntervalNumber, transmissionRiskLevel, rollingPeriod
        )
    }

    override fun toString(): String {
        return String.format(
            Locale.US,
            "TemporaryExposureKey<"
                    + "keyData: %s, "
                    + "rollingStartIntervalNumber: %s, "
                    + "transmissionRiskLevel: %d, "
                    + "rollingPeriod: %d"
                    + ">",
            BigInteger(1, keyData).toString(16),
            Date(TimeUnit.MINUTES.toMillis(rollingStartIntervalNumber * 10L)),
            transmissionRiskLevel,
            rollingPeriod
        )
    }

    /**
     * A builder for [TemporaryExposureKey].
     */
    class TemporaryExposureKeyBuilder() {
        private var keyData = ByteArray(0)
        private var rollingStartIntervalNumber = 0

        @RiskLevel
        private var transmissionRiskLevel: Int = RiskLevel.RISK_LEVEL_INVALID
        private var rollingPeriod = 0
        fun setKeyData(keyData: ByteArray): TemporaryExposureKeyBuilder {
            this.keyData = Arrays.copyOf(keyData, keyData.size)
            return this
        }

        fun setRollingStartIntervalNumber(
            rollingStartIntervalNumber: Int
        ): TemporaryExposureKeyBuilder {
            Preconditions.checkArgument(
                rollingStartIntervalNumber >= 0,
                "rollingStartIntervalNumber (%s) must be >= 0",
                rollingStartIntervalNumber
            )
            this.rollingStartIntervalNumber = rollingStartIntervalNumber
            return this
        }

        fun setTransmissionRiskLevel(
            @RiskLevel transmissionRiskLevel: Int
        ): TemporaryExposureKeyBuilder {
            Preconditions.checkArgument(
                transmissionRiskLevel >= 0 && transmissionRiskLevel <= 8,
                "transmissionRiskLevel (%s) must be >= 0 and <= 8",
                transmissionRiskLevel
            )
            this.transmissionRiskLevel = transmissionRiskLevel
            return this
        }

        fun setRollingPeriod(rollingPeriod: Int): TemporaryExposureKeyBuilder {
            Preconditions.checkArgument(
                rollingPeriod >= 0,
                "rollingPeriod (%s) must be >= 0",
                rollingPeriod
            )
            this.rollingPeriod = rollingPeriod
            return this
        }

        fun build(): TemporaryExposureKey {
            return TemporaryExposureKey(
                keyData, rollingStartIntervalNumber, transmissionRiskLevel, rollingPeriod
            )
        }
    }

    companion object {
        val CREATOR: Parcelable.Creator<TemporaryExposureKey?> =
            object : Parcelable.Creator<TemporaryExposureKey?> {
                override fun createFromParcel(source: Parcel): TemporaryExposureKey {
                    return TemporaryExposureKey(source)
                }

                override fun newArray(size: Int): Array<TemporaryExposureKey?> {
                    return arrayOfNulls(size)
                }
            }
    }
}