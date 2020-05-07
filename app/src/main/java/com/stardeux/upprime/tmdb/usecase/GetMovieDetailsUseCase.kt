package com.stardeux.upprime.tmdb.usecase

import com.stardeux.upprime.tmdb.repository.TmdbRepository
import com.stardeux.upprime.tmdb.repository.model.TmdbDetailsRequest
import com.stardeux.upprime.tmdb.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.usecase.model.MovieDetails

class GetMovieDetailsUseCase(private val tmdbRepository: TmdbRepository) {

    suspend operator fun invoke(tmdbDetailsResponse: TmdbDetailsRequest): MovieDetails {
        return mapToMovieDetails(tmdbRepository.getMovieDetails(tmdbDetailsResponse))
    }
}