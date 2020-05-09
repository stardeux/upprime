package com.stardeux.upprime.tmdb.configuration.usecase

import com.stardeux.upprime.tmdb.configuration.repository.TmdbConfigurationRepository
import com.stardeux.upprime.tmdb.configuration.usecase.model.TmdbConfiguration

class GetTmdbConfigurationUseCase(private val tmdbConfigurationRepository: TmdbConfigurationRepository) {

    suspend fun invoke(): TmdbConfiguration {
        val tmdbConfigurationResponse = tmdbConfigurationRepository.configuration()

        return with(tmdbConfigurationResponse) {

            TmdbConfiguration(
                baseUrl = secureBaseUrl ?: baseUrl ?: DEFAULT_CONFIGURATION.baseUrl,
                backdropSizes = backdropSizes ?: DEFAULT_CONFIGURATION.backdropSizes,
                logoSizes = logoSizes ?: DEFAULT_CONFIGURATION.logoSizes,
                posterSizes = posterSizes ?: DEFAULT_CONFIGURATION.posterSizes
            )
        }
    }

    companion object {
        private val DEFAULT_CONFIGURATION = TmdbConfiguration(
            baseUrl = "https://image.tmdb.org/t/p/",
            backdropSizes = listOf("w300", "w780", "w1280", "original"),
            logoSizes = listOf("w45", "w92", "w154", "w185", "w300", "w500", "original"),
            posterSizes = listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")
        )
    }
}