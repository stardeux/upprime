package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieDetailsResponse

class MovieRepository(private val tmdbMovieApi: TmdbMovieApi) {

    suspend fun getMovieDetails(
        imdbOrTmdbMovieId: String, language: String
    ): TmdbMovieDetailsResponse {
        return tmdbMovieApi.movieDetails(imdbOrTmdbMovieId, language)
    }
}