package com.stardeux.upprime.tmdb.find.repository

import com.stardeux.upprime.tmdb.find.repository.api.TmdbSearchApi
import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchMovieResponse

class SearchMovieRepository (private val tmdbSearchApi: TmdbSearchApi) {

    suspend fun searchMovie(query: String, language: String): TmdbSearchMovieResponse {
        return tmdbSearchApi.searchMovie(query, language)
    }
}