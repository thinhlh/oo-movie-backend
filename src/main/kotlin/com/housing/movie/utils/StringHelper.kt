package com.housing.movie.utils

import java.util.*

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
}