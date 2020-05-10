package com.stardeux.upprime.core.mapper

import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoField

fun mapAmazonDateStringToLocaleDateTime(dateString: String): LocalDateTime {
    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(dateString, LocalDateTime::from)
}

fun mapAmazonDateStringToLocaleDate(dateString: String): LocalDate {
    return mapAmazonDateStringToLocaleDateTime(dateString).toLocalDate()
}

fun mapRuntimeToString(runtimeMinute: Int): String {
    return String.format("%dh %02dm", runtimeMinute / 60, runtimeMinute % 60)
}

fun mapReleaseDateToYear(localDate: LocalDate): String {
    return localDate.get(ChronoField.YEAR).toString()
}


private const val RUNTIME_FORMAT = "HH'h' MM'm'"