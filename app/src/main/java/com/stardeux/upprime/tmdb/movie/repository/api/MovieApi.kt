package com.stardeux.upprime.tmdb.movie.repository.api

import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{imdbMovieId}")
    suspend fun movieDetails(
        @Path("imdbMovieId") imdbMovieId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse
}