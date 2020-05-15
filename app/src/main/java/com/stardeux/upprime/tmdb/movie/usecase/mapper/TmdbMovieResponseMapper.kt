package com.stardeux.upprime.tmdb.movie.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMovieDetails(
    tmdbMovieDetailsResponse: TmdbMovieDetailsResponse, tmdbMovieRequest: TmdbMovieRequest
): MovieDetails {
    return with(tmdbMovieDetailsResponse) {
        MovieDetails(
            tmdbId = requireNotNull(tmdbId),
            imdbId = tmdbMovieRequest.imdbId,
            amazonId = tmdbMovieRequest.amazonId,
            title = title,
            posterUrl = posterUrl,
            mediaReleaseDate = releaseDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = runtime?.takeIf { it > 0 },
            genders = genres?.mapNotNull { it.name },
            nationalities = productionCountries?.mapNotNull { it.name },
            averageRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate,
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}