package com.stardeux.upprime.tmdb.usecase.model

data class MovieDetails(
    val imdbId: String,
    val title: String,
    val posterUrl: String?
)