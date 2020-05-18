package com.stardeux.upprime.tmdb.configuration.repository

import com.stardeux.upprime.tmdb.configuration.repository.api.TmdbConfigurationApi
import com.stardeux.upprime.tmdb.configuration.repository.model.TmdbConfigurationResponse
import com.stardeux.upprime.tmdb.configuration.repository.model.TmdbImageConfigurationResponse

class TmdbConfigurationRepository(private val tmdbConfigurationApi: TmdbConfigurationApi) {

    suspend fun configuration(): TmdbConfigurationResponse {
        return tmdbConfigurationApi.configuration()
    }
}