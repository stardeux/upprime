package com.stardeux.upprime.tmdb.configuration.repository.api

import com.stardeux.upprime.tmdb.configuration.repository.model.TmdbConfigurationResponse
import com.stardeux.upprime.tmdb.configuration.repository.model.TmdbImageConfigurationResponse
import retrofit2.http.GET

interface TmdbConfigurationApi {

    @GET("configuration")
    suspend fun configuration () : TmdbConfigurationResponse
}