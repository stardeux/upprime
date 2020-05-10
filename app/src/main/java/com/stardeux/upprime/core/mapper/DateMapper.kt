package com.stardeux.upprime.core.mapper

import org.threeten.bp.Duration
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoField
import org.threeten.bp.temporal.TemporalField

fun mapAmazonDateStringToLocaleDateTime(dateString: String): LocalDateTime {
    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(dateString, LocalDateTime::from)
}

fun mapAmazonDateStringToLocaleDate(dateString: String): LocalDate {
    return mapAmazonDateStringToLocaleDateTime(dateString).toLocalDate()
}

fun mapRuntimeToString(runtimeMinute: Int): String {
    return Duration.ofMinutes(runtimeMinute.toLong()).toString()
}

fun mapReleaseDateToYear(localDate: LocalDate): String {
    return localDate.get(ChronoField.YEAR).toString()
}