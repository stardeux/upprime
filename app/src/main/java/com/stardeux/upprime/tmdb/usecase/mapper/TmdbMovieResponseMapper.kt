package com.stardeux.upprime.tmdb.usecase.mapper

import com.stardeux.upprime.tmdb.repository.model.TmdbMovieResponse
import com.stardeux.upprime.tmdb.usecase.model.MovieDetails

fun mapToMovieDetails(tmdbMovieResponse: TmdbMovieResponse): MovieDetails {
    return MovieDetails(
        requireNotNull(tmdbMovieResponse.imdbId),
        requireNotNull(tmdbMovieResponse.title),
        requireNotNull(tmdbMovieResponse.posterUrl)
    )
}