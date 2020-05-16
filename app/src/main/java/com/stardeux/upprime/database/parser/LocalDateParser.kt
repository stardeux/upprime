package com.stardeux.upprime.database.parser

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

private val formatter = DateTimeFormatter.ISO_DATE

fun getDatabaseLocalDate(localDate: String): LocalDate {
    return formatter.parse(localDate, LocalDate::from)
}