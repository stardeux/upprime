package com.stardeux.upprime.tmdb.series.repository

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesDetailsResponse

class SeriesRepository(private val tmdbSeriesApi: TmdbSeriesApi) {

    suspend fun getSeriesDetails(tmdbMovieId: TmdbId, language: String): TmdbSeriesDetailsResponse {
        return tmdbSeriesApi.seriesDetails(tmdbMovieId, language)
    }
}