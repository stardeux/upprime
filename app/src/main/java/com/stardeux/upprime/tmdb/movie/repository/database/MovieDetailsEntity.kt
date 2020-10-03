package com.stardeux.upprime.tmdb.movie.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity.Companion.TABLE_MOVIE_NAME
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

@Entity(tableName = TABLE_MOVIE_NAME)
data class MovieDetailsEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = COL_MOVIE_ID) val id: Int = 0,

    @ColumnInfo(name = COL_MOVIE_TMDB_ID) val tmdbId: TmdbId,
    @ColumnInfo(name = COL_MOVIE_IMDB_ID) val imdbId: ImdbId,
    @ColumnInfo(name = COL_MOVIE_TITLE) val title: String?,
    @ColumnInfo(name = COL_MOVIE_ORIGINAL_TITLE) val originalTitle: String?,
    @ColumnInfo(name = COL_MOVIE_POSTER_PATH) val posterPath: String?,
    @ColumnInfo(name = COL_MOVIE_RELEASE_DATE) val releaseDate: LocalDate?,
    @ColumnInfo(name = COL_MOVIE_RUNTIME) val runtime: Int?,
    @ColumnInfo(name = COL_MOVIE_TMDB_RATING) val tmdbRating: Float?,
    @ColumnInfo(name = COL_MOVIE_TMDB_SYNOPSIS) val synopsis: String?,
    @ColumnInfo(name = COL_MOVIE_BACKDROP_PATH) val backdropPath: String?,
    @ColumnInfo(name = COL_MOVIE_GENRES) val genres: String?,
    @ColumnInfo(name = COL_MOVIE_PRODUCTION_COUNTRIES) val productionCountries: String?
) {

    companion object {
        const val TABLE_MOVIE_NAME = "movies"
        const val COL_MOVIE_ID = "movie_id"
        const val COL_MOVIE_TMDB_ID = "tmdb_id"
        const val COL_MOVIE_IMDB_ID = "imdb_id"
        const val COL_MOVIE_TITLE = "title"
        const val COL_MOVIE_ORIGINAL_TITLE = "original_title"
        const val COL_MOVIE_POSTER_PATH = "poster_path"
        const val COL_MOVIE_RELEASE_DATE = "release_date"
        const val COL_MOVIE_RUNTIME = "runtime"
        const val COL_MOVIE_TMDB_RATING = "tmdb_rating"
        const val COL_MOVIE_TMDB_SYNOPSIS = "synopsis"
        const val COL_MOVIE_BACKDROP_PATH = "backdrop_path"
        const val COL_MOVIE_GENRES = "genres"
        const val COL_MOVIE_PRODUCTION_COUNTRIES = "production_countries"
    }

}