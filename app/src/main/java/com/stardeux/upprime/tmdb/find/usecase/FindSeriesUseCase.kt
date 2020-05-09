package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToFindSeries
import com.stardeux.upprime.tmdb.find.usecase.error.SeriesNotFoundException
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries

class FindSeriesUseCase(private val findMediaRepository: FindMediaRepository) {

    suspend fun findSeriesByImdbId(imdbId: String): FindSeries? {
        return findMediaRepository.findMedia(imdbId, "fr").seriesResults?.getOrNull(0)?.let {
            mapToFindSeries(it)
        }

    }
}