package com.tregz.mvvm.core.date

import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun dayOfYear(skeleton: String? = "dMMMMyyyy", at: Date? = null): String {
        @UseExperimental(ExperimentalStdlibApi::class)
        return format(pattern(skeleton), at).capitalize(Locale.getDefault())
    }

    private fun calendar(at: Date?): Calendar {
        return at?.let { Calendar.getInstance().apply { time = at } } ?: Calendar.getInstance()
    }

    private fun format(pattern: String, at: Date?): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(calendar(at).time)
    }

    private fun pattern(skeleton: String?): String {
        return DateFormat.getBestDateTimePattern(Locale.getDefault(), skeleton)
    }

}