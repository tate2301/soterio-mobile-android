package zw.ac.cut.soterio.sble.crypto

import android.media.MediaCodec
import androidx.annotation.Nullable
import com.google.common.base.Preconditions
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class KeyDerivation {
    private val HKDF_OUTPUT_LENGTH = 16
    private val HASH_LENGTH = 32

    /**
     * The algorithm name used for Exposure Notification Cryptography.
     */
    val ALGORITHM_NAME = "HmacSHA256"


    /**
     * Note that this function is for Exposure Notification Cryptography Specification 1.1 only, it
     * only support 16-byte length output.
     */
    @Throws(MediaCodec.CryptoException::class)
    fun hkdfSha256(
        inputKeyingMaterial: ByteArray?,
        @Nullable inputSalt: ByteArray?,
        info: ByteArray?,
        length: Int
    ): ByteArray? {
        return try {
            val mac = Mac.getInstance(ALGORITHM_NAME)
            hkdfSha256(mac, inputKeyingMaterial, inputSalt, info, length)
        } catch (e: NoSuchAlgorithmException) {
            throw e
        }
    }

    /**
     * Note that this function is for Exposure Notification Cryptography Specification 1.1 only, it
     * only support 16-byte length output.
     */
    @Throws(MediaCodec.CryptoException::class)
    fun hkdfSha256(
        mac: Mac,
        inputKeyingMaterial: ByteArray?,
        @Nullable inputSalt: ByteArray?,
        info: ByteArray?,
        length: Int
    ): ByteArray? {
        Preconditions.checkArgument(mac.algorithm == ALGORITHM_NAME)
        if (length != HKDF_OUTPUT_LENGTH) {
            throw NoSuchAlgorithmException("Only support 16-byte.")
        }
        val salt =
            if (inputSalt == null || inputSalt.size == 0) ByteArray(HASH_LENGTH) else inputSalt
        return try {
            hkdfSha256ExpandLength16(mac, hkdfSha256Extract(mac, inputKeyingMaterial, salt), info)
        } catch (e: InvalidKeyException) {
            throw e
        }
    }

    /**
     * The HKDF (RFC 5869) extraction function, using the SHA-256 hash function. The output PRK is
     * calculated as follows: PRK = HMAC-SHA256(salt, IKM), i.e. salt as the key of hmac-sha256, and
     * ikm (input keying material) as the message.
     */
    @Throws(InvalidKeyException::class)
    private fun hkdfSha256Extract(
        mac: Mac,
        inputKeyingMaterial: ByteArray?,
        salt: ByteArray
    ): ByteArray {
        mac.init(SecretKeySpec(salt, ALGORITHM_NAME))
        return mac.doFinal(inputKeyingMaterial)
    }

    /**
     * HKDF (RFC 5869) expansion function, using the SHA-256 hash function, the output size is
     * 16-byte.
     */
    @Throws(InvalidKeyException::class)
    private fun hkdfSha256ExpandLength16(
        mac: Mac,
        pseudoRandomKey: ByteArray,
        info: ByteArray?
    ): ByteArray? {
        // For length being 16 cases, counter always equals to 0x01.
        val counter = byteArrayOf(0x01)
        mac.init(SecretKeySpec(pseudoRandomKey, ALGORITHM_NAME))
        mac.update(info)
        return Arrays.copyOf(mac.doFinal(counter), HKDF_OUTPUT_LENGTH)
    }
}