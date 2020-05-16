package com.stardeux.upprime.movie.usecase

import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(
    private val getUnconfiguredMovieDetailsUseCase: GetUnconfiguredMovieDetailsUseCase,
    private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase
) {
    suspend operator fun invoke(tmdbMovieRequest: TmdbMovieRequest): MovieDetails {
        val configuration = getTmdbConfigurationUseCase()
        val movieDetails = getUnconfiguredMovieDetailsUseCase(tmdbMovieRequest)

        val configuredPosterUrl = movieDetails.posterUrl?.let { configuration.baseImageUrl + it }
        return movieDetails.copy(posterUrl = configuredPosterUrl)
    }
}