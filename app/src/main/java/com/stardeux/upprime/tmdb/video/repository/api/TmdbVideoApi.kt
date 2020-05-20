package com.stardeux.upprime.tmdb.video.repository.api

import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbVideoApi {

    @GET("movie/{imdbOrTmdbMovieId}/videos")
    suspend fun movieVideos(
        @Path("imdbOrTmdbMovieId") imdbMovieId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse
}