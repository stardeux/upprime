package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend fun getCachedSeriesDetails(imdbId: ImdbId): SeriesDetails? {
        return seriesRepository.getCachedSeriesDetails(imdbId)
    }

    suspend operator fun invoke(tmdbSeriesRequest: TmdbSeriesRequest): SeriesDetails {
        return seriesRepository.getSeriesDetails(tmdbSeriesRequest, tmdbSeriesRequest.language)
    }

}