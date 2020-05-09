package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.repository.mapper.mapToFindSeries
import com.stardeux.upprime.tmdb.find.usecase.error.SeriesNotFoundException
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries

class FindSeriesUseCase(private val findMediaRepository: FindMediaRepository) {

    suspend fun findSeriesByImdbId(imdbId: String): FindSeries? {
        val seriesResults = findMediaRepository.findMedia(imdbId, "fr").seriesResults?.getOrNull(0)
        if (seriesResults == null) {
            throw SeriesNotFoundException(imdbId)
        } else {
            return mapToFindSeries(seriesResults)
        }
    }
}