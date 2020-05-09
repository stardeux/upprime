package com.stardeux.upprime.core.mapper

import org.threeten.bp.Duration
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun mapDateStringToLocaleDateTime(dateString: String): LocalDateTime {
    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(dateString, LocalDateTime::from)
}

fun mapRuntimeToString(runtimeMinute: Int): String {
    return Duration.ofMinutes(runtimeMinute.toLong()).toString()
}