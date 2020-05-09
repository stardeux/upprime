package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.movie.repository.TmdbMovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(private val tmdbMovieRepository: TmdbMovieRepository) {

    suspend operator fun invoke(imdbMediaId: String): MovieDetails {
        return mapToMovieDetails(tmdbMovieRepository.getMovieDetails(imdbMediaId, "fr"))
    }

}