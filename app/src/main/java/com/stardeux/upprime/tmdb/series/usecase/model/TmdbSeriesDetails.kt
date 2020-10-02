package com.stardeux.upprime.tmdb.series.usecase.model

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class TmdbSeriesDetails(
    val tmdbId: TmdbId,
    val imdbId: ImdbId,
    val name: String?,
    val originalName: String?,
    val posterPath: String?,
    val mediaReleaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genres: List<String>?,
    val nationalities: List<String>?,
    val tmdbRating: Float?,
    val synopsis: String?,
    val backdropPath: String?
)