package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieApi
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse

class TmdbMovieRepository(private val tmdbMovieApi: TmdbMovieApi) {

    suspend fun getMovieDetails(
        imdbMediaId: String, language: String
    ): TmdbMovieDetailsResponse {
        return tmdbMovieApi.movieDetails(imdbMediaId, language)
    }
}