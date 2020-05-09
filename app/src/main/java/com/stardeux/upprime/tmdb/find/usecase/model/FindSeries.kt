package com.stardeux.upprime.tmdb.find.usecase.model

import org.threeten.bp.LocalDate

data class FindSeries(
    val tmdbId: String,
    val originalName: String?,
    val name: String?,
    val posterPath: String?,
    val originalLanguage: String,
    val originCountry: List<String>,
    val firstAirDate: LocalDate?,
    val voteAverage: Float?
)