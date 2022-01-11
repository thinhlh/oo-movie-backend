package com.housing.movie.utils

import com.housing.movie.exceptions.ConversionException
import org.apache.http.util.TextUtils
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.temporal.TemporalAccessor
import java.util.*
import java.util.concurrent.TimeUnit


object DateTimeHelper {
    object Format {
        const val DD_MM_YYYY = "dd/MM/yyyy"
        const val DD__MM__YYYY = "dd-MM-yyyy"
        const val YYYY_MM_DD = "yyyy/MM/dd"
        const val HH_MM = "HH:mm"
    }

    fun stringToCalendar(dateString: String?, format: String = Format.DD_MM_YYYY): Calendar? {
        try {
            if (dateString == null) return null

            val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
            val calendar = Calendar.getInstance()

            calendar.time = simpleDateFormat.parse(dateString)

            return calendar
        } catch (e: Exception) {
            throw ConversionException("Unable to convert date")
        }
    }

    fun calendarToString(calendar: Calendar, format: String = Format.DD_MM_YYYY): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)

        return simpleDateFormat.format(calendar.time)
    }

    fun timeStampToCalendar(timestamp: Long?): Calendar? {
        timestamp ?: return null

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp

        return calendar
    }
}