package com.stardeux.upprime.amazon.expired.repository.api

import com.stardeux.upprime.amazon.common.repository.model.MediaPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExpiredApi {

    @GET("expired")
    suspend fun expired(
        @Query("country") country: String, @Query("days") days: Int, @Query("page") page: Int
    ): MediaPageResponse
}