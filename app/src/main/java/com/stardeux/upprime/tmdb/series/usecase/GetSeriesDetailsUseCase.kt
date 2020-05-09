package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.series.repository.SeriesRepository
import com.stardeux.upprime.tmdb.series.usecase.mapper.mapToSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetSeriesDetailsUseCase(
    private val SeriesRepository: SeriesRepository,
    private val findSeriesUseCase: FindSeriesUseCase
) {

    suspend operator fun invoke(imdbSeriesId: String): SeriesDetails {
        return try {
            getSeriesDetails(imdbSeriesId)
        } catch (exception: Exception) {
            val findSeries = findSeriesUseCase.findSeriesByImdbId(imdbSeriesId)
            getSeriesDetails(findSeries.tmdbId)
        }
    }

    private suspend fun getSeriesDetails (imdbOrTmdbSeriesId: String): SeriesDetails {
        return mapToSeriesDetails(SeriesRepository.getSeriesDetails(imdbOrTmdbSeriesId, "fr"))
    }

}