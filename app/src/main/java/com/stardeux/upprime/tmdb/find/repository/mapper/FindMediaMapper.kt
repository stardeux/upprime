package com.stardeux.upprime.tmdb.find.repository.mapper

import com.stardeux.upprime.tmdb.find.repository.model.FindMovieResponse
import com.stardeux.upprime.tmdb.find.repository.model.FindSeriesResponse
import com.stardeux.upprime.tmdb.find.usecase.model.FindMovie
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries

fun mapToFindMovie(findMovieResponse: FindMovieResponse): FindMovie {
    return with(findMovieResponse) {
        FindMovie(
            tmdbId = requireNotNull(tmdbId),
            title = title,
            originalTitle = originalTitle,
            posterPath = posterPath,
            originalLanguage = originalLanguage,
            releaseDate = releaseDate,
            voteAverage = voteAverage
        )
    }
}

fun mapToFindSeries(findSeriesResponse: FindSeriesResponse): FindSeries {
    return with(findSeriesResponse) {
        FindSeries(
            tmdbId = requireNotNull(tmdbId),
            originalName = originalName,
            name = name,
            posterPath = posterPath,
            originalLanguage = originalLanguage,
            originCountry = originCountry,
            firstAirDate = firstAirDate,
            voteAverage = voteAverage
        )
    }
}