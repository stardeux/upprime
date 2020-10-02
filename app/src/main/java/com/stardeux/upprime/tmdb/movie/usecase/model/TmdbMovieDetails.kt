package com.stardeux.upprime.tmdb.movie.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class TmdbMovieDetails(
    val tmdbId: TmdbId,
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val mediaReleaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genres: List<String>?,
    val nationalities: List<String>?,
    val tmdbRating: Float?,
    val amazonReleaseDate: LocalDate,
    val synopsis: String?,
    val backdropPath: String?
)