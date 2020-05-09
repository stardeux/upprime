package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.movie.repository.MovieRepository
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val findMovieUseCase: FindMovieUseCase
) {

    suspend operator fun invoke(imdbMovieId: String): MovieDetails {
        return try {
            getMovieDetails(imdbMovieId)
        } catch (exception: Exception) {
            val findMovie = findMovieUseCase.findMovieByImdbId(imdbMovieId)
            getMovieDetails(findMovie.tmdbId)
        }
    }

    private suspend fun getMovieDetails (imdbOrTmdbMovieId: String): MovieDetails {
        return mapToMovieDetails(movieRepository.getMovieDetails(imdbOrTmdbMovieId, "fr"))
    }

}