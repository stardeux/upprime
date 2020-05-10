package com.stardeux.upprime.tmdb.movie.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
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
            runtimeMinutes = runtime,
            genders = genres?.mapNotNull { it.name },
            nationalities = productionCountries?.mapNotNull { it.name },
            averageRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate
        )
    }
}