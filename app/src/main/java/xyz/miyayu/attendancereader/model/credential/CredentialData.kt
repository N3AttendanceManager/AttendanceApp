package xyz.miyayu.attendancereader.model.credential

import android.util.Log
import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException

data class CredentialData(val jwtToken: String?) {
    /**
     * コードできるか検証する
     */
    init {
        if (jwtToken != null) {
            try {
                JWT.decode(jwtToken)
                Log.d(this::class.simpleName, "JWT Token is valid.\n token: $jwtToken")
            } catch (e: JWTDecodeException) {
                Log.e(this::class.simpleName, "Recieved Jwt Token: $jwtToken", e)
                throw e
            }
        }
    }
}