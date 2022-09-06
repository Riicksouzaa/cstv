package com.codenome.cstv.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun Calendar.getActualYear(): Int = this.get(Calendar.YEAR)


fun String.parseDate(): LocalDateTime? {
    val inputFormat =
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    return LocalDateTime.parse(this, inputFormat)
}