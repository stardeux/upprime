package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.stardeux.upprime.tmdb.common.model.database.MovieGenreCrossRef.Companion.TABLE_MOVIE_GENRE_REF_NAME
import com.stardeux.upprime.tmdb.common.model.database.SeriesGenreCrossRef.Companion.TABLE_SERIES_GENRE_REF_NAME
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity

@Entity(
    tableName = TABLE_MOVIE_GENRE_REF_NAME,
    primaryKeys = [MovieGenreCrossRef.COL_MOVIE_ID, MovieGenreCrossRef.COL_PRODUCTION_COUNTRY_ID]
)
data class MovieGenreCrossRef(
    @ColumnInfo(name = COL_MOVIE_ID) val movieId: Long,
    @ColumnInfo(name = COL_PRODUCTION_COUNTRY_ID) val productionCountryId: Long
) {

    companion object {
        const val TABLE_MOVIE_GENRE_REF_NAME = "movie_genre"

        const val COL_MOVIE_ID = "movie_id"
        const val COL_PRODUCTION_COUNTRY_ID = "genre_id"
    }

}