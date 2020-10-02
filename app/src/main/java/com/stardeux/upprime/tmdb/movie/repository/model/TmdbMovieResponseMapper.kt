package com.stardeux.upprime.tmdb.movie.repository.model

import com.stardeux.upprime.database.parser.*
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails

fun mapToMovieDetailsEntity(tmdbMovieDetails: TmdbMovieDetails): MovieDetailsEntity {
    return with(tmdbMovieDetails) {
        MovieDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = title,
            originalTitle = originalTitle,
            posterPath = posterPath,
            releaseDate = mediaReleaseDate,
            runtime = runtimeMinutes,
            genres = genres?.let (::formatDatabaseGenres),
            productionCountries = nationalities?.let(::formatDatabaseProductionCountries),
            tmdbRating = tmdbRating,
            amazonReleaseDate = amazonReleaseDate,
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    } 
}

