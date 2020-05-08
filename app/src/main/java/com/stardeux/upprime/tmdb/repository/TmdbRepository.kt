package com.stardeux.upprime.tmdb.repository

import com.stardeux.upprime.tmdb.repository.api.TmdbApi
import com.stardeux.upprime.tmdb.repository.model.TmdbDetailsRequest
import com.stardeux.upprime.tmdb.repository.model.TmdbMovieDetailsResponse

class TmdbRepository(private val tmdbApi: TmdbApi) {

    suspend fun getMovieDetails(tmdbDetailsResponse: TmdbDetailsRequest): TmdbMovieDetailsResponse {
        return tmdbApi.movieDetails(tmdbDetailsResponse.imdbMediaId, tmdbDetailsResponse.language)
    }
}