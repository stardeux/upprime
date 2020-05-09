package com.stardeux.upprime.tmdb.series.repository

import com.stardeux.upprime.tmdb.series.api.TmdbSeriesApi
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesDetailsResponse

class SeriesRepository(private val tmdbSeriesApi: TmdbSeriesApi) {

    suspend fun getSeriesDetails(tmdbMovieId: String, language: String): TmdbSeriesDetailsResponse {
        return tmdbSeriesApi.seriesDetails(tmdbMovieId, language)
    }
}