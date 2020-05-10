package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.mapper.mapToSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetUnconfiguredSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(tmdbSeriesRequest: TmdbSeriesRequest): SeriesDetails {
        return mapToSeriesDetails(seriesRepository.getSeriesDetails(tmdbSeriesRequest.tmdbId, "fr"), tmdbSeriesRequest)
    }

}