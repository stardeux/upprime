package com.stardeux.upprime.series.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class SeriesDetails(
    val tmdbId: TmdbId,
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val name: String?,
    val posterUrl: String?,
    val mediaReleaseDate: LocalDate?,
    val runtimeMinutes: Int?,
    val genders: List<String>?,
    val nationalities: List<String>?,
    val averageRating: Float?,
    val amazonReleaseDate: LocalDate,
    val synopsis: String?,
    val backdropPath: String?
)