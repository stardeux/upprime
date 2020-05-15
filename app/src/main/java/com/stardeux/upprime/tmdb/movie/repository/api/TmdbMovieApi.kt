package com.stardeux.upprime.tmdb.movie.repository.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbMovieApi {

    /**
     * movie endpoint only support TMDB id officially but it is also working with IMDB id
     */
    @GET("movie/{imdbOrTmdbMovieId}")
    suspend fun movieDetails(
        @Path("imdbOrTmdbMovieId") imdbMovieId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse
}