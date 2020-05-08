package com.stardeux.upprime.tmdb.find.api

import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FindApi {

    @GET("find/{imdbId}/")
    suspend fun find(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbMovieDetailsResponse
}