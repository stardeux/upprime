package com.stardeux.upprime.tmdb.common.mapper

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun mapToLocalDate(localDate: String): LocalDate {
    return DateTimeFormatter.ISO_LOCAL_DATE.parse(localDate, LocalDate::from)
}