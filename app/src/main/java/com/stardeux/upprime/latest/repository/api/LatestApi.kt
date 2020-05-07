package com.stardeux.upprime.latest.repository.api

import com.stardeux.upprime.latest.repository.model.MediaPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestApi {

    @GET("new")
    suspend fun new(
        @Query("country") country: String,
        @Query("days") days: Int,
        @Query("page") page: Int
    ): MediaPageResponse

}