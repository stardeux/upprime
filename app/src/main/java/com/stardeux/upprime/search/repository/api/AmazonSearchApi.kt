package com.stardeux.upprime.search.repository.api

import retrofit2.http.Query

interface AmazonSearchApi {

    fun search(
        @Query("title") title: String,
        @Query("country") country: String,
        @Query("yearstart") yearStart: Int,
        @Query("yearend") yearEnd: Int,
        @Query("page") page: Int
    ): SearchMediaContainerResponse

}