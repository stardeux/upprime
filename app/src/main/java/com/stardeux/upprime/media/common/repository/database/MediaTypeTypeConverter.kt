package com.stardeux.upprime.media.common.repository.database

import androidx.room.TypeConverter
import com.stardeux.upprime.core.model.MediaType

object MediaTypeTypeConverter {

    private const val MEDIA_TYPE_MOVIE_DB_VALUE = "movie"
    private const val MEDIA_TYPE_SERIES_DB_VALUE = "series"

    @TypeConverter
    @JvmStatic
    fun mediaTypeToDatabaseValue(mediaType: MediaType): String {
        return when (mediaType) {
            MediaType.MOVIE -> MEDIA_TYPE_MOVIE_DB_VALUE
            MediaType.SERIES -> MEDIA_TYPE_SERIES_DB_VALUE
        }
    }

    @TypeConverter
    @JvmStatic
    fun databaseValueToMediaType(databaseValue: String): MediaType {
        return when (databaseValue) {
            MEDIA_TYPE_MOVIE_DB_VALUE -> MediaType.MOVIE
            MEDIA_TYPE_SERIES_DB_VALUE -> MediaType.SERIES
            else -> throw IllegalArgumentException("Unknown database value $databaseValue")
        }
    }

}