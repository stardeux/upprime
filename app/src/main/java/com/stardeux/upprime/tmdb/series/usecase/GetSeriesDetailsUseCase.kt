package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.mapper.mapToSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val seriesRepository: SeriesRepository
) {

    suspend operator fun invoke(imdbId: ImdbId, tmdbSeriesId: TmdbId): SeriesDetails {
        return mapToSeriesDetails(seriesRepository.getSeriesDetails(tmdbSeriesId, "fr"), imdbId)
    }

}