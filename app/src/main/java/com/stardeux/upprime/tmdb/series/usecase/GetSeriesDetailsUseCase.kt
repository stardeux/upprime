package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.mapper.mapToSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(tmdbSeriesId: String): SeriesDetails {
        return mapToSeriesDetails(seriesRepository.getSeriesDetails(tmdbSeriesId, "fr"), tmdbSeriesId)
    }

}