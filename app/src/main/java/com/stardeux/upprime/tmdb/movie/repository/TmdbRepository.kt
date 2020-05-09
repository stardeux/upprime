package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.tmdb.movie.repository.api.MovieApi
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse

class TmdbRepository(private val tmdbApi: MovieApi) {

    suspend fun getMovieDetails(
        imdbMediaId: String, language: String
    ): TmdbMovieDetailsResponse {
        return tmdbApi.movieDetails(imdbMediaId, language)
    }
}