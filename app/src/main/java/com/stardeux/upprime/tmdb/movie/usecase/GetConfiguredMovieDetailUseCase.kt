package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetConfiguredMovieDetailUseCase(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) {
    suspend fun invoke(imdbId: ImdbId, tmdbId: TmdbId?) {

    }
}