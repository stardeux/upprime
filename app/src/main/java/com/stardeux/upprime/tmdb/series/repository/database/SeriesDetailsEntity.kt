package com.stardeux.upprime.tmdb.series.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity.Companion.TABLE_SERIES_NAME
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity(tableName = TABLE_SERIES_NAME)
data class SeriesDetailsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_SERIES_ID) val id: Int = 0,

    @ColumnInfo(name = COL_SERIES_TMDB_ID) val tmdbId: String,
    @ColumnInfo(name = COL_SERIES_IMDB_ID) val imdbId: String,
    @ColumnInfo(name = COL_SERIES_TITLE) val title: String?,
    @ColumnInfo(name = COL_SERIES_ORIGINAL_TITLE) val originalTitle: String?,
    @ColumnInfo(name = COL_SERIES_POSTER_PATH) val posterPath: String?,
    @ColumnInfo(name = COL_SERIES_RELEASE_DATE) val releaseDate: LocalDate?,
    @ColumnInfo(name = COL_SERIES_RUNTIME) val runtime: Int?,
    @ColumnInfo(name = COL_SERIES_TMDB_RATING) val tmdbRating: Float?,
    @ColumnInfo(name = COL_SERIES_TMDB_SYNOPSIS) val synopsis: String?,
    @ColumnInfo(name = COL_SERIES_BACKDROP_PATH) val backdropPath: String?,
    @ColumnInfo(name = COL_SERIES_GENRES) val genres: String?,
    @ColumnInfo(name = COL_SERIES_PRODUCTION_COUNTRIES) val productionCountries: String?
) {

    companion object {
        const val TABLE_SERIES_NAME = "series"
        const val COL_SERIES_ID = "series_id"
        const val COL_SERIES_TMDB_ID = "tmdb_id"
        const val COL_SERIES_IMDB_ID = "imdb_id"
        const val COL_SERIES_TITLE = "title"
        const val COL_SERIES_ORIGINAL_TITLE = "original_title"
        const val COL_SERIES_POSTER_PATH = "poster_path"
        const val COL_SERIES_RELEASE_DATE = "release_date"
        const val COL_SERIES_RUNTIME = "runtime"
        const val COL_SERIES_TMDB_RATING = "tmdb_rating"
        const val COL_SERIES_TMDB_SYNOPSIS = "synopsis"
        const val COL_SERIES_BACKDROP_PATH = "backdrop_path"
        const val COL_SERIES_GENRES = "genres"
        const val COL_SERIES_PRODUCTION_COUNTRIES = "production_countries"
    }

}