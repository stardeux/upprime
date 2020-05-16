package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stardeux.upprime.tmdb.common.model.database.MovieProductionCountryCrossRef.Companion.TABLE_MOVIE_PRODUCTION_COUNTRY_CROSS_REF_NAME

@Entity(
    tableName = TABLE_MOVIE_PRODUCTION_COUNTRY_CROSS_REF_NAME,
    primaryKeys = [MovieProductionCountryCrossRef.COL_MOVIE_ID, MovieProductionCountryCrossRef.COL_PRODUCTION_COUNTRY_ID]
)
data class MovieProductionCountryCrossRef(
    @ColumnInfo(name = COL_MOVIE_ID) val movieId: Long,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ID) val productionCountryId: Long
) {
    companion object {
        const val TABLE_MOVIE_PRODUCTION_COUNTRY_CROSS_REF_NAME = "movie_production_country"

        const val COL_MOVIE_ID = "movie_id"
        const val COL_PRODUCTION_COUNTRY_ID = "production_country_id"
    }
}