package com.stardeux.upprime.tmdb.movie.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMovieDetails(tmdbMovieDetailsResponse: TmdbMovieDetailsResponse, imdbId: String): MovieDetails {
    return with(tmdbMovieDetailsResponse) {
        MovieDetails(
            tmdbId = requireNotNull(tmdbId),
            imdbId = imdbId,
            title = title,
            posterUrl = posterUrl,
            releaseDate = releaseDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = runtime,
            genders = genres?.mapNotNull { it.name },
            nationalities = productionCountries?.mapNotNull { it.name },
            averageRating = voteAverage
        )
    }
}