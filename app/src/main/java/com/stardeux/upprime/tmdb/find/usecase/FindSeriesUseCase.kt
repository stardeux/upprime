package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToFindSeries
import com.stardeux.upprime.tmdb.find.usecase.error.SeriesNotFoundException
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries
import java.util.*

class FindSeriesUseCase(
    private val findMediaRepository: FindMediaRepository,
    private val locale: Locale
) {

    suspend fun findSeriesByImdbId(imdbId: String): FindSeries? {
        return findMediaRepository.findMedia(imdbId, locale.language).seriesResults?.getOrNull(0)?.let {
            mapToFindSeries(it)
        }

    }
}