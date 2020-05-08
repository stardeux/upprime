package com.stardeux.upprime.tmdb.repository.api

import com.stardeux.upprime.tmdb.repository.model.TmdbMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/{imdbMovieId}")
    suspend fun movieDetails(
        @Path("imdbMovieId") imdbMovieId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse

    @GET("tv/{tmdbSeriesId}")
    suspend fun seriesDetails(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse

    @GET("find/{imdbId}/")
    suspend fun find(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse
}