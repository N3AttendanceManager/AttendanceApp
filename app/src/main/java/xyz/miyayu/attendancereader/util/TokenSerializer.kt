package xyz.miyayu.attendancereader.util

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.crypto.tink.Aead
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AeadFactory
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import xyz.miyayu.attendancereader.model.credential.CredentialData
import java.io.InputStream
import java.io.OutputStream
import java.security.GeneralSecurityException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TokenSerializer @Inject constructor(
    @ApplicationContext applicationContext: Context
): Serializer<CredentialData> {
    private val aead: Aead by lazy {
        AeadConfig.register()
        val keySetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(
                applicationContext,
                "master_key_set", // keySetName
                "master_key_preference" // prefFileName
            )
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri("android-keystore://master_key")
            .build().keysetHandle
        AeadFactory.getPrimitive(keySetHandle)
    }

    override val defaultValue: CredentialData = CredentialData("")

    override suspend fun readFrom(input: InputStream): CredentialData {
        val encrypted = input.readBytes()

        try {
            val decrypted = aead.decrypt(encrypted, null)

            return CredentialData(String(decrypted))
        } catch (exception: GeneralSecurityException) {
            throw CorruptionException("Cannot decrypt.", exception)
        }
    }

    override suspend fun writeTo(t: CredentialData, output: OutputStream) {
        val encrypted = aead.encrypt(t.jwtToken.toByteArray(), null)
        output.write(encrypted)
    }
}