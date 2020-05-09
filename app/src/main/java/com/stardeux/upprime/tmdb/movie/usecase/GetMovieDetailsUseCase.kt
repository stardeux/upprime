package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository
) {

    suspend operator fun invoke(imdbOrTmdbMovieId: String): MovieDetails {
        return mapToMovieDetails(movieRepository.getMovieDetails(imdbOrTmdbMovieId, "fr"))
    }

}