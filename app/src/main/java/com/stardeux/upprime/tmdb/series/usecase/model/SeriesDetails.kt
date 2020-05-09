package com.stardeux.upprime.tmdb.series.usecase.model

import org.threeten.bp.LocalDate

data class SeriesDetails(
    val imdbId: String,
    val name: String?,
    val posterUrl: String?,
    val releaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genders: List<String>?,
    val nationalities: List<String>?,
    val averageRating: Float?
)