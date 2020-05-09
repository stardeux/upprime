package com.stardeux.upprime.tmdb.find.repository

import com.stardeux.upprime.tmdb.find.repository.api.TmdbSearchApi
import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchSeriesResponse

class SearchSeriesRepository (private val tmdbSearchApi: TmdbSearchApi) {

    suspend fun searchSeries(query: String, language: String): TmdbSearchSeriesResponse {
        return tmdbSearchApi.searchSeries(query, language)
    }
}