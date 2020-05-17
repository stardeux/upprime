package com.stardeux.upprime.series.repository

import com.stardeux.upprime.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.series.repository.mapper.SeriesDetailsMapper
import com.stardeux.upprime.series.repository.mapper.mapToSeriesDetailsEntity
import com.stardeux.upprime.series.usecase.model.SeriesDetails
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest

class SeriesRepository(
    private val seriesLocalDataSource: SeriesLocalDataSource,
    private val seriesRemoteDataSource: SeriesRemoteDataSource,
    private val seriesDetailsMapper: SeriesDetailsMapper
) {

    suspend fun getSeriesDetails(tmdbSeriesRequest: TmdbSeriesRequest, language: String): SeriesDetails {
        val cached = seriesLocalDataSource.getSeries(tmdbSeriesRequest.imdbId)
        return if (cached != null) {
            seriesDetailsMapper.mapToSeriesDetails(cached)
        } else {
            seriesDetailsMapper.mapToSeriesDetails(
                seriesRemoteDataSource.getSeriesDetails(tmdbSeriesRequest.tmdbId, language),
                tmdbSeriesRequest
            ).also { seriesLocalDataSource.insert(mapToSeriesDetailsEntity(it)) }
        }
    }
}