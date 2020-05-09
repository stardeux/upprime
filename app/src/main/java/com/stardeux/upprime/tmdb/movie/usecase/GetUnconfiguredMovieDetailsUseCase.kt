package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetUnconfiguredMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(imdbId: ImdbId, tmdbId: TmdbId?): MovieDetails {
        val id = tmdbId ?: imdbId
        return mapToMovieDetails(movieRepository.getMovieDetails(id, "fr"), imdbId)
    }

}