package com.codenome.cstv.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.TimeUnit

fun Calendar.getActualYear(): Int = this.get(Calendar.YEAR)


fun String.parseDate(): LocalDateTime? {
    val inputFormat =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return LocalDateTime.parse(this, inputFormat)
}

fun LocalDateTime.toCalendar(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.time = Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
    return calendar
}


fun Calendar.getTripleDate(): Triple<Int, Int, Int> =
    Triple(this.getActualDay(), this.getActualMonth(), this.getActualYear())

fun Calendar.getTripleStringDate(): Triple<String, String, String> =
    Triple(
        this.getActualDay().toString().padStart(2, '0'),
        this.getActualMonth().toString().padStart(2, '0'),
        this.getActualYear().toString().padStart(2, '0')
    )

fun Calendar.getActualDay(): Int = this.get(Calendar.DAY_OF_MONTH)

fun Calendar.getActualMonth(): Int = this.get(Calendar.MONTH)

fun Calendar.getDaysBetween(start: Calendar) =
    TimeUnit.MILLISECONDS.toDays(kotlin.math.abs(start.timeInMillis - this.timeInMillis))

fun Calendar.getStringDayAndMonth(): String {
    val today = this.getTripleStringDate()
    return "${today.first}.${today.second}"
}

fun Calendar.getMonthsBetween(start: Calendar): Int {
    val startDate = start.timeInMillis
    val today = this.timeInMillis

    val newDate = Calendar.getInstance()
    newDate.timeInMillis = kotlin.math.abs(today - startDate)
    val day = newDate.get(Calendar.DAY_OF_MONTH) - 1
    val years = newDate.get(Calendar.YEAR) - 1970
    val month = newDate.get(Calendar.MONTH) + (12 * years)

    return if (day > 0) month + 1 else month
}

fun Calendar.getStringDate(): String {
    val today = this.getTripleDate()
    return "${today.first}-${today.second}-${today.third}"
}