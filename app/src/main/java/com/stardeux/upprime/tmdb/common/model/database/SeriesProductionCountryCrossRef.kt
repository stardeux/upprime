package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stardeux.upprime.tmdb.common.model.database.SeriesProductionCountryCrossRef.Companion.TABLE_SERIES_PRODUCTION_COUNTRY_CROSS_REF_NAME

@Entity(
    tableName = TABLE_SERIES_PRODUCTION_COUNTRY_CROSS_REF_NAME,
    primaryKeys = [SeriesProductionCountryCrossRef.COL_SERIES_ID, SeriesProductionCountryCrossRef.COL_PRODUCTION_COUNTRY_ID]
)
data class SeriesProductionCountryCrossRef(
    @ColumnInfo(name = COL_SERIES_ID) val seriesId: Long,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ID) val productionCountryId: Long
) {
    companion object {
        const val TABLE_SERIES_PRODUCTION_COUNTRY_CROSS_REF_NAME = "series_production_country"

        const val COL_SERIES_ID = "series_id"
        const val COL_PRODUCTION_COUNTRY_ID = "production_country_id"
    }
}
