package com.stardeux.upprime.tmdb.video.repository.api

import com.stardeux.upprime.tmdb.video.repository.api.model.TmdbVideoContainerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbVideoApi {

    @GET("movie/{imdbOrTmdbMovieId}/videos")
    suspend fun movieVideos(
        @Path("imdbOrTmdbMovieId") imdbOrTmdbMovieId: String, @Query("language") language: String
    ): TmdbVideoContainerResponse

    @GET("tv/{tmdbSeriesId}/videos")
    suspend fun seriesVideos(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbVideoContainerResponse
}