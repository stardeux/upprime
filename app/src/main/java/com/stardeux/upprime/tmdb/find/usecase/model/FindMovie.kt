package com.stardeux.upprime.tmdb.find.usecase.model

data class FindMovie(
    val tmdbId: String,
    val title: String?,
    val originalTitle: String?,
    val posterPath: String?,
    val originalLanguage: String?,
    val releaseDate: String?,
    val voteAverage: Float?
)