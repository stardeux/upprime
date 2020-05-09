package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailUseCase(
    private val getUnconfiguredMovieDetailsUseCase: GetUnconfiguredMovieDetailsUseCase,
    private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase
) {
    suspend operator fun invoke(imdbId: ImdbId, tmdbId: TmdbId?): MovieDetails {
        val configuration = getTmdbConfigurationUseCase()
        val movieDetails = getUnconfiguredMovieDetailsUseCase(imdbId, tmdbId)

        val configuredPosterUrl = configuration.baseImageUrl + movieDetails.posterUrl
        return movieDetails.copy(posterUrl = configuredPosterUrl)
    }
}