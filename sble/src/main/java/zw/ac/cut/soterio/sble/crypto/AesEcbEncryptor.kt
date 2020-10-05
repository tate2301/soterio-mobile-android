package zw.ac.cut.soterio.sble.crypto

import android.annotation.SuppressLint
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.*
import javax.crypto.spec.SecretKeySpec


/**
 * Encryptor that encapsultes aes/ecb encryption, but separates initialization and encryption steps.
 *
 *
 * If used repeatedly, call [.create] and [.init] sparingly.
 */
class AesEcbEncryptor private constructor(private val cipher: Cipher) {
    /**
     * Initializes encryption with provided key.
     */
    @Throws(CryptoException::class)
    fun init(key: ByteArray?) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"))
        } catch (e: InvalidKeyException) {
            throw CryptoException(e)
        }
    }

    /**
     * Encrypts provided data.
     */
    @Throws(CryptoException::class)
    fun encrypt(data: ByteArray?): ByteArray {
        return try {
            cipher.doFinal(data)
        } catch (e: BadPaddingException) {
            throw CryptoException(e)
        } catch (e: IllegalBlockSizeException) {
            throw CryptoException(e)
        }
    }

    /**
     * Encrypts provided data to a given output buffer.
     */
    @Throws(CryptoException::class)
    fun encrypt(data: ByteArray, output: ByteArray): ByteArray {
        return try {
            cipher.doFinal(data,  /*inputOffset =*/0, data.size, output,  /* outputOffset =*/0)
            output
        } catch (e: BadPaddingException) {
            throw CryptoException(e)
        } catch (e: IllegalBlockSizeException) {
            throw CryptoException(e)
        } catch (e: ShortBufferException) {
            throw CryptoException(e)
        }
    }

    companion object {
        @SuppressLint("GetInstance")
        @Throws(CryptoException::class)
        fun create(): AesEcbEncryptor {
            return try {
                AesEcbEncryptor(Cipher.getInstance("AES/ECB/NoPadding"))
            } catch (e: NoSuchAlgorithmException) {
                throw CryptoException(e)
            } catch (e: NoSuchPaddingException) {
                throw CryptoException(e)
            }
        }
    }
}