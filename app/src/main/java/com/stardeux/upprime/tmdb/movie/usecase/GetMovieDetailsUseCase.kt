package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(imdbId: String, tmdbId: String?): MovieDetails {
        val id = tmdbId ?: imdbId
        return mapToMovieDetails(movieRepository.getMovieDetails(id, "fr"), imdbId)
    }

}