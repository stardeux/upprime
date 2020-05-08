package com.stardeux.upprime.tmdb.series.api

import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("tv/{tmdbSeriesId}")
    suspend fun seriesDetails(
        @Path("tmdbSeriesId") tmdbSeriesId: String, @Query("language") language: String
    ): TmdbSeriesResponse
}