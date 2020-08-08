package com.cuncisboss.simplearticlecwm.util

import com.dustinredmond.BCrypt
import java.security.MessageDigest


object PasswordHelper {
    fun String.toMD5(): String {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        return bytes.toHex()
    }

    fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }

    fun generateHashedPass(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }

    fun isValid(confirmPassword: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(confirmPassword, hashedPassword)
    }
}