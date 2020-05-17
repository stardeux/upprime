package com.stardeux.upprime.series.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.series.repository.SeriesRepository
import com.stardeux.upprime.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend fun getCachedSeriesDetails(imdbId: ImdbId): SeriesDetails? {
        return seriesRepository.getCachedSeriesDetails(imdbId)
    }

    suspend operator fun invoke(tmdbSeriesRequest: TmdbSeriesRequest): SeriesDetails {
        return seriesRepository.getSeriesDetails(tmdbSeriesRequest, "fr")
    }

}