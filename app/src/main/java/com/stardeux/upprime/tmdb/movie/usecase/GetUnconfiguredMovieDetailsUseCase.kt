package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetUnconfiguredMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(tmdbMovieRequest: TmdbMovieRequest): MovieDetails {
        return movieRepository.getMovieDetails(tmdbMovieRequest, "fr")
    }

}