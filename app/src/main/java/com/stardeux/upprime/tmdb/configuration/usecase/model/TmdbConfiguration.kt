package com.stardeux.upprime.tmdb.configuration.usecase.model

data class TmdbConfiguration (
    val basePosterUrl: String,
    val backdropSizes: List<String>,
    val logoSizes: List<String>,
    val posterSizes: List<String>
)