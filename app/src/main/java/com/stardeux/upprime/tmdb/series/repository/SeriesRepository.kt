package com.stardeux.upprime.tmdb.series.repository

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.tmdb.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.tmdb.series.repository.model.SeriesDetailsMapper
import com.stardeux.upprime.tmdb.series.repository.model.mapToSeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.repository.model.SeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest

class SeriesRepository(
    private val seriesLocalDataSource: SeriesLocalDataSource,
    private val seriesRemoteDataSource: SeriesRemoteDataSource,
    private val seriesDetailsMapper: SeriesDetailsMapper
) {

    suspend fun getSeriesDetails(
        tmdbSeriesRequest: TmdbSeriesRequest, language: String
    ): SeriesDetails {
        val cached = getCachedSeriesDetails(tmdbSeriesRequest.imdbId)
        return cached ?: getRemoteSeriesDetails(tmdbSeriesRequest, language)
            .also { cacheSeriesDetails(it) }
    }

    suspend fun cacheSeriesDetails(seriesDetails: SeriesDetails) {
        seriesLocalDataSource.insert(mapToSeriesDetailsEntity(seriesDetails))
    }

    suspend fun getRemoteSeriesDetails(
        tmdbSeriesRequest: TmdbSeriesRequest, language: String
    ): SeriesDetails {
        return seriesDetailsMapper.mapToSeriesDetails(
            seriesRemoteDataSource.getSeriesDetails(tmdbSeriesRequest.tmdbId, language),
            tmdbSeriesRequest
        )
    }

    suspend fun getCachedSeriesDetails(imdbId: ImdbId): SeriesDetails? {
        return seriesLocalDataSource.getSeries(imdbId)
            ?.let { seriesDetailsMapper.mapToSeriesDetails(it) }
    }
}