package com.stardeux.upprime.movie.usecase

import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.movie.repository.MovieRepository
import com.stardeux.upprime.movie.usecase.model.MovieDetails

class GetUnconfiguredMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(tmdbMovieRequest: TmdbMovieRequest): MovieDetails {
        return movieRepository.getMovieDetails(tmdbMovieRequest, "fr")
    }

}