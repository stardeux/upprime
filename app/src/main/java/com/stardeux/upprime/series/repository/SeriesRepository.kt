package com.stardeux.upprime.series.repository

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.movie.repository.mapper.mapToMovieDetails
import com.stardeux.upprime.movie.repository.mapper.mapToMovieDetailsEntity
import com.stardeux.upprime.series.repository.api.SeriesRemoteDataSource
import com.stardeux.upprime.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.series.repository.database.SeriesLocalDataSource
import com.stardeux.upprime.series.usecase.model.SeriesDetails
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest

class SeriesRepository(
    private val seriesLocalDataSource: SeriesLocalDataSource,
    private val seriesRemoteDataSource: SeriesRemoteDataSource
) {

    suspend fun getSeriesDetails(tmdbMovieRequest: TmdbSeriesRequest, language: String): SeriesDetails {
        val cached = seriesLocalDataSource.getSeries(tmdbMovieRequest.imdbId)
        return if (cached != null) {
            mapToMovieDetails(cached)
        } else {
            mapToMovieDetails(
                seriesRemoteDataSource.getSeriesDetails(tmdbMovieRequest.imdbId, language),
                tmdbMovieRequest
            ).also { seriesLocalDataSource.insert(mapToMovieDetailsEntity(it)) }
        }
    }
}