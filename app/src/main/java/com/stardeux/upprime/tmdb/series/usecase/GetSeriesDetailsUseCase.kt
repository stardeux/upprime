package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val getUnconfiguredSeriesDetailsUseCase: GetUnconfiguredSeriesDetailsUseCase,
    private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase
) {
    suspend operator fun invoke(tmdbSeriesRequest: TmdbSeriesRequest): SeriesDetails {
        val configuration = getTmdbConfigurationUseCase()
        val seriesDetails = getUnconfiguredSeriesDetailsUseCase(tmdbSeriesRequest)

        val configuredPosterUrl = seriesDetails.posterUrl?.let { configuration.baseImageUrl + it }
        return seriesDetails.copy(posterUrl = configuredPosterUrl)
    }
}