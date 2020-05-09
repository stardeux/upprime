package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetImdbMovieDetailsUseCase(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val findMovieUseCase: FindMovieUseCase
) {

    suspend operator fun invoke(imdbId: String, name: String?): MovieDetails {
        return try {
            getMovieDetailsUseCase.invoke(imdbId)
        } catch (exception: Exception) {
            val tmdbId = findMovieUseCase.findMovieByImdbId(imdbId)?.tmdbId
            if (tmdbId != null) {
                getMovieDetailsUseCase(tmdbId)
            } else {
                throw MovieNotFoundException(imdbId)
            }
        }
    }
}