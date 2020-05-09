package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.tmdb.common.TmdbDetailsRequest
import com.stardeux.upprime.tmdb.movie.repository.api.MovieApi
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse

class TmdbRepository(private val tmdbApi: MovieApi) {

    suspend fun getMovieDetails(tmdbDetailsResponse: TmdbDetailsRequest): TmdbMovieDetailsResponse {
        return tmdbApi.movieDetails(tmdbDetailsResponse.imdbMediaId, tmdbDetailsResponse.language)
    }
}