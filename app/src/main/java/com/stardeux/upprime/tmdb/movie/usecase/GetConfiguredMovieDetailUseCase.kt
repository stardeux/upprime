package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.configuration.usecase.GetTmdbConfigurationUseCase
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetConfiguredMovieDetailUseCase(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getTmdbConfigurationUseCase: GetTmdbConfigurationUseCase
) {
    suspend operator fun invoke(imdbId: ImdbId, tmdbId: TmdbId?): MovieDetails {
        val configuration = getTmdbConfigurationUseCase()
        val movieDetails = getMovieDetailsUseCase(imdbId, tmdbId)

        val configuredPosterUrl = configuration.baseImageUrl + movieDetails.posterUrl
        return movieDetails.copy(posterUrl = configuredPosterUrl)
    }
}