package com.housing.movie.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.housing.movie.features.auth.domain.entity.LoginRequest
import org.springframework.http.HttpRequest
import java.util.*
import javax.servlet.http.HttpServletRequest

object StringHelper {

    const val upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    const val lowerAlphabet = "abcdefghijklmnopqrstuvwxyz"
    const val numbers = "0123456789"

    fun randomString(length: Int): String {

        val alphaNumeric: String = upperAlphabet + lowerAlphabet + numbers;

        val sb: StringBuilder = StringBuilder();

        val random: Random = Random()

        for (i in 0..length) {
            val index = random.nextInt(alphaNumeric.length)

            val randomChar = alphaNumeric.get(index)

            sb.append(randomChar)
        }

        val randomString = sb.toString();
        return randomString
    }

    fun <T> mapRequestBodyToObject(request: HttpServletRequest, clazz: Class<T>): T {
        val stringBuilder = StringBuilder()
        val reader = request.reader
        reader.use { it ->
            var line: String?
            while (it.readLine().also { line = it } != null) {
                stringBuilder.append(line).append(' ')
            }
        }
        val jsonString = stringBuilder.toString()

        return ObjectMapper().readValue(jsonString, clazz)
    }
}