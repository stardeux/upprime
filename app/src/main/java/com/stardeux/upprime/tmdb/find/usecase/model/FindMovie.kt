package com.stardeux.upprime.tmdb.find.usecase.model

import org.threeten.bp.LocalDate

data class FindMovie(
    val tmdbId: String,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val originalLanguage: String?,
    val releaseDate: LocalDate?,
    val voteAverage: Float?
)