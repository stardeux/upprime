package com.stardeux.upprime.core.mapper

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import org.threeten.bp.temporal.ChronoField
import java.text.NumberFormat
import java.util.*

fun mapRuntimeToString(runtimeMinute: Int?): String? {
    return runtimeMinute?.let {
        val hours = it / 60
        if (hours >= 1) {
            String.format("%dh%02dm", hours, it % 60)
        } else {
            String.format("%dm", it)
        }

    }
}

fun mapReleaseDateToYear(localDate: LocalDate?): String? {
    return localDate?.get(ChronoField.YEAR)?.toString()
}

fun formatToHumanReadableMonthDay(localDate: LocalDate): String {
    return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(localDate)
}

fun mapToRatingString(rating: Float?): String? {
    return rating?.let {
        val numberFormat = NumberFormat.getInstance(Locale.getDefault()).apply {
            maximumFractionDigits = 1
            minimumFractionDigits = 0
        }
        return numberFormat.format(it) + " / 10"
    }
}
