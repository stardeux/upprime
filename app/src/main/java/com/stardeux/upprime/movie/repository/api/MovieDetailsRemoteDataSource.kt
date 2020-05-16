package com.stardeux.upprime.movie.repository.api

class MovieDetailsRemoteDataSource(
    private val tmdbMovieApi: TmdbMovieApi
) {
    suspend fun getMovieDetails(
        imdbOrTmdbMovieId: String, language: String
    ): TmdbMovieDetailsResponse {
        return tmdbMovieApi.movieDetails(imdbOrTmdbMovieId, language)
    }
}