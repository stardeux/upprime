package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val getUnconfiguredSeriesDetailsUseCase: GetUnconfiguredSeriesDetailsUseCase,
    private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase
) {
    suspend operator fun invoke(imdbId: ImdbId, tmdbId: TmdbId): SeriesDetails {
        val configuration = getTmdbConfigurationUseCase()
        val seriesDetails = getUnconfiguredSeriesDetailsUseCase(imdbId, tmdbId)

        val configuredPosterUrl = seriesDetails.posterUrl?.let { configuration.baseImageUrl + it }
        return seriesDetails.copy(posterUrl = configuredPosterUrl)
    }
}