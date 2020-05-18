package com.stardeux.upprime.tmdb.movie.repository.mapper

import com.stardeux.upprime.database.parser.*
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMovieDetailsEntity(movieDetails: MovieDetails): MovieDetailsEntity {
    return with(movieDetails) {
        MovieDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = title,
            originalTitle = originalTitle,
            posterUrl = posterUrl,
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

