package com.stardeux.upprime.series.repository

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.series.repository.api.TmdbSeriesDetailsResponse

class SeriesRepository(private val tmdbSeriesApi: TmdbSeriesApi) {

    suspend fun getSeriesDetails(tmdbMovieId: TmdbId, language: String): TmdbSeriesDetailsResponse {
        return tmdbSeriesApi.seriesDetails(tmdbMovieId, language)
    }
}