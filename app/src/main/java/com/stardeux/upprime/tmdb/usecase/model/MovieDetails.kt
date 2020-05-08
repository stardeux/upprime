package com.stardeux.upprime.tmdb.usecase.model

import org.threeten.bp.LocalDate

data class MovieDetails(
    val imdbId: String,
    val title: String?,
    val posterUrl: String?,
    val releaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genders: List<String>?,
    val nationalities: List<String>?,
    val averageRating: Float?
)