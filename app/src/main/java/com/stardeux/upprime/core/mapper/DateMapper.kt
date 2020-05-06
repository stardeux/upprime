package com.stardeux.upprime.core.mapper

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun mapToDate(dateString: String): LocalDateTime {
    return DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(dateString, LocalDateTime::from)
}