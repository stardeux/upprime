package com.stardeux.upprime.tmdb.configuration.usecase

import com.stardeux.upprime.tmdb.configuration.repository.TmdbConfigurationRepository
import com.stardeux.upprime.tmdb.configuration.usecase.model.TmdbConfiguration
import java.lang.Exception

class GetTmdbConfigurationUseCase(private val tmdbConfigurationRepository: TmdbConfigurationRepository) {

    private var configuration: TmdbConfiguration? = null

    @Synchronized suspend operator fun invoke(): TmdbConfiguration {
        return configuration ?: fetchConfiguration().also { configuration = it }
    }

    private suspend fun fetchConfiguration(): TmdbConfiguration {
        return try {
            val tmdbConfigurationResponse = tmdbConfigurationRepository.configuration()
            with(tmdbConfigurationResponse) {

                val imageBaseUrl = secureBaseUrl ?: baseUrl ?: DEFAULT_CONFIGURATION.basePosterUrl
                val posterSizeList = posterSizes ?: DEFAULT_CONFIGURATION.posterSizes
                val backdropSizes = backdropSizes ?: DEFAULT_CONFIGURATION.backdropSizes

                TmdbConfiguration(
                    basePosterUrl = imageBaseUrl + posterSizeList[1],
                    baseBackdropUrl = imageBaseUrl + backdropSizes[1],
                    backdropSizes = backdropSizes,
                    logoSizes = logoSizes ?: DEFAULT_CONFIGURATION.logoSizes,
                    posterSizes = posterSizeList
                )
            }
        } catch (exception: Exception) {
            DEFAULT_CONFIGURATION
        }
    }

    companion object {
        private val DEFAULT_CONFIGURATION = TmdbConfiguration(
            basePosterUrl = "https://image.tmdb.org/t/p/w154",
            baseBackdropUrl = "https://image.tmdb.org/t/p/w780",
            backdropSizes = listOf("w300", "w780", "w1280", "original"),
            logoSizes = listOf("w45", "w92", "w154", "w185", "w300", "w500", "original"),
            posterSizes = listOf("w92", "w154", "w185", "w342", "w500", "w780", "original")
        )
    }
}