package com.stardeux.upprime.tmdb.series.usecase.model

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class SeriesDetails(
    val tmdbId: TmdbId,
    val imdbId: ImdbId,
    val name: String?,
    val posterUrl: String?,
    val releaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genders: List<String>?,
    val nationalities: List<String>?,
    val averageRating: Float?
)