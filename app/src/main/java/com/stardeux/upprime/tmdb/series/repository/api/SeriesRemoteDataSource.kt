package com.stardeux.upprime.tmdb.series.repository.api

import com.stardeux.upprime.core.model.TmdbId

class SeriesRemoteDataSource(
    private val tmdbSeriesApi: TmdbSeriesApi
) {
    suspend fun getSeriesDetails(
        tmdbId: TmdbId, language: String
    ): TmdbSeriesDetailsResponse {
        return tmdbSeriesApi.seriesDetails(tmdbId, language)
    }
}