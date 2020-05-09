package com.stardeux.upprime.tmdb.find.repository.api

import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchSeriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbSearchApi {

    @GET("search/tv/")
    suspend fun searchSeries(
        @Path("query") query: String, @Query("language") language: String
    ): TmdbSearchSeriesResponse

}