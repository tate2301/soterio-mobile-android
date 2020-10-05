package zw.ac.cut.soterio.sble.crypto

import android.os.Build
import androidx.annotation.RequiresApi
import zw.ac.cut.soterio.sble.Constants
import zw.ac.cut.soterio.sble.crypto.TemporaryExposureKey.TemporaryExposureKeyBuilder
import zw.ac.cut.soterio.sble.features.ContactTracingFeature
import java.nio.charset.StandardCharsets.UTF_8
import java.security.SecureRandom
import java.time.Instant
import java.util.concurrent.TimeUnit


class ProvideTemporaryKeys {
    private var ENInterval: Int = Constants.ENInterval
    private var EKRollingPeriod: Int = Constants.EKRollingPeriod
    private val AES_BLOCK_SIZE = 16
    private val rollingStartIntervalNumber = 0
    private val rollingEndIntervalNumber = 0
    private val encryptor: AesEcbEncryptor? = null
    private lateinit var aesPadding: ByteArray

    @RequiresApi(Build.VERSION_CODES.O)
    fun getENIntervalNumber(timestamp: Instant): Long {
        return (timestamp.toEpochMilli()/(TimeUnit.MINUTES.toMillis(10)))
    }

    fun generateKey(rollingStartIntervalNumber: Int, rollingPeriod: Int): TemporaryExposureKey {
        // TODO - Each key is randomly and independently generated using a cryptographic random number generator
        // Associated with ENIntervalNumber at time t
        // Key generated once every 24 hours since rolling period is 24

        val random = SecureRandom()
        val key = ByteArray(ContactTracingFeature.temporaryTracingKeySizeBytes())
        random.nextBytes(key)
        return TemporaryExposureKeyBuilder()
            .setKeyData(key)
            .setRollingStartIntervalNumber(rollingStartIntervalNumber)
            .setRollingPeriod(rollingPeriod)
            .build()

    }

    fun generateKey(rollingStartIntervalNumber: Int): TemporaryExposureKey? {
        return generateKey(
            rollingStartIntervalNumber,
            ContactTracingFeature.tkRollingPeriodMultipleOfIdRollingPeriod()
        )
    }

    fun getRollingIdentifierKey(temporaryExposureKeyData: ByteArray, rpikHkdfInfoString: String): ByteArray? {
        // TODO - Derived from current Temporary Key
        // RPIKi ← HKDF(tek.i, NUL L, UTF8("EN-RPIK"),16)

        return CryptoProvider.KeyDerivation.hkdfSha256(
            temporaryExposureKeyData,
            /* inputSalt =*/ null,
            rpikHkdfInfoString.toByteArray(UTF_8),
            16
        );

    }

    fun getRollingIdentifiers(RPIK: ByteArray) {
        // TODO - Derive from RPIK
        // RPIi, j ← AES128(RPIKi , PaddedDataj)



    }
}