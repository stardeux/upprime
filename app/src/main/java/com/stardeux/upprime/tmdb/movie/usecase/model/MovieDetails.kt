package com.stardeux.upprime.tmdb.movie.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class MovieDetails(
    val tmdbId: TmdbId,
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val title: String?,
    val posterUrl: String?,
    val mediaReleaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genders: List<String>?,
    val nationalities: List<String>?,
    val averageRating: Float?,
    val amazonReleaseDate: LocalDate
)