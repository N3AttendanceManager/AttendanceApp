package xyz.miyayu.attendancereader.model.credential

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
            } catch (e: JWTDecodeException) {
                throw e
            }
        }
    }
}