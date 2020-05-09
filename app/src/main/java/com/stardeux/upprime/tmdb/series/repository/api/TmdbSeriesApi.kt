package com.stardeux.upprime.tmdb.series.repository.api

import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbSeriesApi {

    @GET("tv/{tmdbSeriesId}")
    suspend fun seriesDetails(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbSeriesDetailsResponse
}