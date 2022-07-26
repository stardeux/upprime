package com.stardeux.upprime.tmdb.configuration.usecase.model

data class TmdbConfiguration (
    val baseImageUrl: String,
    val basePosterUrl: String,
    val baseBackdropUrl: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>
)