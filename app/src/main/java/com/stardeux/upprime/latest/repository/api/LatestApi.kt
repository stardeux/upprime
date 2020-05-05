package com.stardeux.upprime.latest.repository.api

import com.stardeux.upprime.latest.repository.model.MediaPageResponse
import retrofit2.http.GET

interface LatestApi {

    @GET("new")
    suspend fun new(): MediaPageResponse

}