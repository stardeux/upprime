package com.stardeux.upprime.tmdb.configuration.usecase

import com.stardeux.upprime.tmdb.configuration.repository.TmdbConfigurationRepository
import com.stardeux.upprime.tmdb.configuration.usecase.model.TmdbConfiguration

class GetTmdbConfigurationUseCase(private val tmdbConfigurationRepository: TmdbConfigurationRepository) {

    private var configuration: TmdbConfiguration? = null

    suspend operator fun invoke(): TmdbConfiguration {
        return configuration ?: fetchConfiguration().also { configuration = it }
    }

    private suspend fun fetchConfiguration(): TmdbConfiguration {
        val tmdbConfigurationResponse = tmdbConfigurationRepository.configuration()

        return with(tmdbConfigurationResponse) {

            TmdbConfiguration(
                baseImageUrl = secureBaseUrl ?: baseUrl ?: DEFAULT_CONFIGURATION.baseImageUrl,
                backdropSizes = backdropSizes ?: DEFAULT_CONFIGURATION.backdropSizes,
                logoSizes = logoSizes ?: DEFAULT_CONFIGURATION.logoSizes,
                posterSizes = posterSizes ?: DEFAULT_CONFIGURATION.posterSizes
            )
        }
    }

    companion object {
        private val DEFAULT_CONFIGURATION = TmdbConfiguration(
            baseImageUrl = "https://image.tmdb.org/t/p/",
            backdropSizes = listOf("w300", "w780", "w1280", "original"),
            logoSizes = listOf("w45", "w92", "w154", "w185", "w300", "w500", "original"),
            posterSizes = listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")
        )
    }
}