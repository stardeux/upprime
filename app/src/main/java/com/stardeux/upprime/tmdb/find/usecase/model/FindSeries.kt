package com.stardeux.upprime.tmdb.find.usecase.model

data class FindSeries(
    val tmdbId: String,
    val originalName: String?,
    val name: String?,
    val posterPath: String?,
    val originalLanguage: String,
    val originCountry: String,
    val firstAirDate: String?,
    val voteAverage: Float?
)