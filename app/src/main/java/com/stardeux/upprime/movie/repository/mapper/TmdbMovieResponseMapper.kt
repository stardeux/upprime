package com.stardeux.upprime.movie.repository.mapper

import com.stardeux.upprime.database.parser.*
import com.stardeux.upprime.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest

fun mapToMovieDetails(movieDetailsEntity: MovieDetailsEntity): MovieDetails {
    return with(movieDetailsEntity){
        MovieDetails(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = title,
            originalTitle = originalTitle,
            posterUrl = posterUrl,
            mediaReleaseDate = releaseDate?.let(::parseDatabaseLocalDate),
            runtimeMinutes = runtime,
            genres = genres?.let (::parseDatabaseGenres),
            nationalities = productionCountries?.let(::parseDatabaseProductionCountries),
            tmdbRating = tmdbRating,
            amazonReleaseDate = parseDatabaseLocalDate(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    } 
}


fun mapToMovieDetailsEntity(movieDetails: MovieDetails): MovieDetailsEntity {
    return with(movieDetails) {
        MovieDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = title,
            originalTitle = originalTitle,
            posterUrl = posterUrl,
            releaseDate = mediaReleaseDate?.let(::formatDatabaseLocalDate),
            runtime = runtimeMinutes,
            genres = genres?.let (::formatDatabaseGenres),
            productionCountries = nationalities?.let(::formatDatabaseProductionCountries),
            tmdbRating = tmdbRating,
            amazonReleaseDate = formatDatabaseLocalDate(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    } 
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
            originalTitle = originalTitle,
            posterUrl = posterUrl,
            mediaReleaseDate = releaseDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = runtime?.takeIf { it > 0 },
            genres = genres?.mapNotNull { it.name },
            nationalities = productionCountries?.mapNotNull { it.name },
            tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate,
            synopsis = synopsis.takeIf { it?.isNotBlank() == true },
            backdropPath = backdropPath
        )
    }
}