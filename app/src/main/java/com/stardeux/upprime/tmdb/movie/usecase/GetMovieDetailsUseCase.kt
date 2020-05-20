package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import java.util.*

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val locale: Locale
) {

    suspend operator fun invoke(tmdbMovieRequest: TmdbMovieRequest): MovieDetails {
        return movieRepository.getMovieDetails(tmdbMovieRequest, locale.language)
    }

}