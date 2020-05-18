package com.stardeux.upprime.tmdb.common.mapper

import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase

class PosterMapper(private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase) {

    suspend fun getCompletePosterUrl(posterUrl: String?): String? {
        return posterUrl?.let { getTmdbConfigurationUseCase().basePosterUrl + it }
    }

}