package com.raahi.reddit.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Util {

    fun convertUtc2Local(utcTime: String?): String? {
        var time = ""
        if (utcTime != null) {
            val utcFormatter =
                SimpleDateFormat("HH", Locale.getDefault())
            utcFormatter.timeZone = TimeZone.getTimeZone("UTC")
            var gpsUTCDate: Date? = null
            try {
                gpsUTCDate = utcFormatter.parse(utcTime)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val localFormatter =
                SimpleDateFormat("HH", Locale.getDefault())
            localFormatter.timeZone = TimeZone.getDefault()
            assert(gpsUTCDate != null)
            time = localFormatter.format(gpsUTCDate!!.time)
        }
        return time
    }
}