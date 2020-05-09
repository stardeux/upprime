package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.movie.repository.TmdbRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(private val tmdbRepository: TmdbRepository) {

    suspend operator fun invoke(tmdbDetailsResponse: TmdbDetailsRequest): MovieDetails {
        return mapToMovieDetails(
            tmdbRepository.getMovieDetails(
                tmdbDetailsResponse.imdbMediaId,
                tmdbDetailsResponse.language
            )
        )
    }

    data class TmdbDetailsRequest(
        val imdbMediaId: String, val language: String
    )
}