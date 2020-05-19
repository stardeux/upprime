package com.stardeux.upprime.tmdb.series.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class TmdbSeriesRequest(
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val tmdbId: TmdbId,
    val amazonReleaseDate: LocalDate,
    val name: String?,
    val language: String
)