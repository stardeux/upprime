package com.stardeux.upprime.tmdb.series.repository

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.tmdb.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.tmdb.series.repository.model.SeriesDetailsMapper
import com.stardeux.upprime.tmdb.series.repository.model.mapToSeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest

class SeriesRepository(
    private val seriesLocalDataSource: SeriesLocalDataSource,
    private val seriesRemoteDataSource: SeriesRemoteDataSource,
    private val seriesDetailsMapper: SeriesDetailsMapper
) {

    suspend fun getSeriesDetails(
        tmdbSeriesRequest: TmdbSeriesRequest, language: String
    ): TmdbSeriesDetails {
        val cached = getCachedSeriesDetails(tmdbSeriesRequest.imdbId)
        return cached ?: getRemoteSeriesDetails(tmdbSeriesRequest, language)
            .also { cacheSeriesDetails(it) }
    }

    suspend fun cacheSeriesDetails(tmdbSeriesDetails: TmdbSeriesDetails) {
        seriesLocalDataSource.insert(mapToSeriesDetailsEntity(tmdbSeriesDetails))
    }

    suspend fun getRemoteSeriesDetails(
        tmdbSeriesRequest: TmdbSeriesRequest, language: String
    ): TmdbSeriesDetails {
        return seriesDetailsMapper.mapToSeriesDetails(
            seriesRemoteDataSource.getSeriesDetails(tmdbSeriesRequest.tmdbId, language),
            tmdbSeriesRequest
        )
    }

    suspend fun getCachedSeriesDetails(imdbId: ImdbId): TmdbSeriesDetails? {
        return seriesLocalDataSource.getSeries(imdbId)
            ?.let { seriesDetailsMapper.mapToSeriesDetails(it) }
    }
}