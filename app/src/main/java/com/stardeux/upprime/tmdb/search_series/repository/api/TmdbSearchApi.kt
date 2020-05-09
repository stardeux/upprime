package com.stardeux.upprime.tmdb.search_series.repository.api

import com.stardeux.upprime.tmdb.find.repository.model.TmdbFindMediaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbSearchApi {

    @GET("search/tv/")
    suspend fun find(
        @Path("query") query: String, @Query("language") language: String
    ): TmdbFindMediaResponse

}