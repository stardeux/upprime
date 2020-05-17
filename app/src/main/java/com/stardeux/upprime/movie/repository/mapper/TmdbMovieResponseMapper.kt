package com.stardeux.upprime.movie.repository.mapper

import com.stardeux.upprime.database.parser.*
import com.stardeux.upprime.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest

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

