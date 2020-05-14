package com.stardeux.upprime.media.expired.repository.api

import com.stardeux.upprime.media.common.repository.model.MediaPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExpiredApi {

    @GET("deleted")
    suspend fun expired(
        @Query("country") country: String, @Query("days") days: Int, @Query("page") page: Int
    ): MediaPageResponse
}