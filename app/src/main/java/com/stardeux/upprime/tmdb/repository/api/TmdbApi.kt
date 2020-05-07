package com.stardeux.upprime.tmdb.repository.api

import com.stardeux.upprime.tmdb.repository.model.TmdbMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/{imdbMovieId}")
    suspend fun movieDetails(
        @Path("imdbMovieId") imdbMovieId: String, @Query("language") language: String
    ): TmdbMovieResponse
}