package com.stardeux.upprime.tmdb.find.repository.api

import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchMovieResponse
import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbSearchApi {

    @GET("search/tv/")
    suspend fun searchSeries(
        @Query("query") query: String,
        @Query("language") language: String
    ): TmdbSearchSeriesResponse


    @GET("search/movie/")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("language") language: String
    ): TmdbSearchMovieResponse

}