package com.stardeux.upprime.media.common.repository.database

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

object LocalDateTimeTypeConverter {

    private val formatter = DateTimeFormatter.ISO_DATE

    @TypeConverter
    @JvmStatic
    fun formatLocalDate(localDate: LocalDate?): String? {
        return localDate?.format(formatter)
    }

    @TypeConverter
    @JvmStatic
    fun parseLocalDate(localDate: String?): LocalDate? {
        return localDate?.let { formatter.parse(it, LocalDate::from) }
    }
}