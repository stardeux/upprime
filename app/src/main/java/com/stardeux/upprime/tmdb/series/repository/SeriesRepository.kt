package com.stardeux.upprime.tmdb.series.repository

import com.stardeux.upprime.tmdb.series.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesResponse

class SeriesRepository(private val tmdbSeriesApi: TmdbSeriesApi) {

    suspend fun getSeriesDetails(tmdbMovieId: String, language: String): TmdbSeriesResponse {
        return tmdbSeriesApi.seriesDetails(tmdbMovieId, language)
    }
}