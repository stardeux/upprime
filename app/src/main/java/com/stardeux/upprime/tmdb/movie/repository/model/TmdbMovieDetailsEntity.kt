package com.stardeux.upprime.tmdb.movie.repository.model

import androidx.room.ColumnInfo
import com.stardeux.upprime.tmdb.common.model.GenreResponse
import com.stardeux.upprime.tmdb.common.model.ProductionCountryResponse

data class TmdbMovieDetailsEntity(
    @ColumnInfo(name = "id") val tmdbId: String?,
    @ColumnInfo(name = "imdb_id") val imdbId: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "original_title") val original_title: String?,
    @ColumnInfo(name = "poster_path") val posterUrl: String?,
    @ColumnInfo(name = "release_date") val releaseDate: String?,
    @ColumnInfo(name = "runtime") val runtime: Int?,
    @ColumnInfo(name = "genres") val genres: List<GenreResponse>?,
    @ColumnInfo(name = "production_countries") val productionCountries: List<ProductionCountryResponse>?,
    @ColumnInfo(name = "vote_average") val voteAverage: Float?,
    @ColumnInfo(name = "vote_count") val voteCount: Int?,
    @ColumnInfo(name = "overview") val synopsis: String?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String?
)