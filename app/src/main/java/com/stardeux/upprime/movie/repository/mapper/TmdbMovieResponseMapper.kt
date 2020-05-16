package com.stardeux.upprime.movie.repository.mapper

import com.stardeux.upprime.database.parser.getDatabaseGenres
import com.stardeux.upprime.database.parser.getDatabaseLocalDate
import com.stardeux.upprime.database.parser.getDatabaseProductionCountries
import com.stardeux.upprime.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest

fun mapToMovieDetails(movieDetailsEntity: MovieDetailsEntity): MovieDetails {
    return MovieDetails(
        tmdbId = movieDetailsEntity.tmdbId,
        imdbId = movieDetailsEntity.imdbId,
        amazonId = movieDetailsEntity.amazonId,
        title = movieDetailsEntity.title,
        posterUrl = movieDetailsEntity.posterUrl,
        mediaReleaseDate = movieDetailsEntity.releaseDate?.let(::getDatabaseLocalDate),
        runtimeMinutes = movieDetailsEntity.runtime,
        genders = movieDetailsEntity.genres?.let (::getDatabaseGenres),
        nationalities = movieDetailsEntity.productionCountries?.let(::getDatabaseProductionCountries),
        tmdbRating = movieDetailsEntity.tmdbRating,
        amazonReleaseDate = getDatabaseLocalDate(movieDetailsEntity.amazonReleaseDate),
        synopsis = movieDetailsEntity.synopsis,
        backdropPath = movieDetailsEntity.backdropPath
    )
}

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
            tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate,
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}