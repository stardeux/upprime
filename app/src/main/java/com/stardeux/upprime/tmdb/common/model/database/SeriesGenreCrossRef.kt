package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stardeux.upprime.tmdb.common.model.database.SeriesGenreCrossRef.Companion.TABLE_SERIES_GENRE_REF_NAME
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity

@Entity(tableName = TABLE_SERIES_GENRE_REF_NAME, primaryKeys = [SeriesDetailsEntity.COL_SERIES_ID, GenreEntity.COL_GENRE_ID])
data class SeriesGenreCrossRef(
    @ColumnInfo(name = COL_SERIES_ID) val seriesId: Long,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ID) val productionCountryId: Long
) {
    companion object {
        const val TABLE_SERIES_GENRE_REF_NAME = "series_genre"

        const val COL_SERIES_ID = "series_id"
        const val COL_PRODUCTION_COUNTRY_ID = "series_id"
    }
}
