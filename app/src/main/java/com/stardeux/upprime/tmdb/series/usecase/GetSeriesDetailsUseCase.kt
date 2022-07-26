package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails

class GetSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend fun getCachedSeriesDetails(imdbId: ImdbId): TmdbSeriesDetails? {
        return seriesRepository.getCachedSeriesDetails(imdbId)
    }

    suspend operator fun invoke(tmdbSeriesRequest: TmdbSeriesRequest): TmdbSeriesDetails {
        return seriesRepository.getSeriesDetails(tmdbSeriesRequest, tmdbSeriesRequest.language)
    }

}