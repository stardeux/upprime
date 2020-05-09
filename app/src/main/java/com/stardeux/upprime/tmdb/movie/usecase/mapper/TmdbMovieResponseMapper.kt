package com.stardeux.upprime.tmdb.movie.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMovieDetails(tmdbMovieDetailsResponse: TmdbMovieDetailsResponse): MovieDetails {
    return with(tmdbMovieDetailsResponse) {
        MovieDetails(
            imdbId = requireNotNull(imdbId),
            title = requireNotNull(title),
            posterUrl = requireNotNull(posterUrl),
            releaseDate = releaseDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = runtime,
            genders = genres?.mapNotNull { it.name },
            nationalities = productionCountries?.mapNotNull { it.name },
            averageRating = voteAverage
        )
    }
}