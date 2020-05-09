package com.stardeux.upprime.tmdb.find.repository.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.find.repository.model.TmdbFindMovieResponse
import com.stardeux.upprime.tmdb.find.repository.model.TmdbFindSeriesResponse
import com.stardeux.upprime.tmdb.find.usecase.model.FindMovie
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries

fun mapToFindMovie(tmdbFindMovieResponse: TmdbFindMovieResponse): FindMovie {
    return with(tmdbFindMovieResponse) {
        FindMovie(
            tmdbId = requireNotNull(tmdbId),
            title = title,
            originalTitle = originalTitle,
            posterPath = posterPath,
            originalLanguage = originalLanguage,
            releaseDate = releaseDate?.let { mapTmdbLocalDate(it) },
            voteAverage = voteAverage
        )
    }
}

fun mapToFindSeries(tmdbFindSeriesResponse: TmdbFindSeriesResponse): FindSeries {
    return with(tmdbFindSeriesResponse) {
        FindSeries(
            tmdbId = requireNotNull(tmdbId),
            originalName = originalName,
            name = name,
            posterPath = posterPath,
            originalLanguage = originalLanguage,
            originCountry = originCountry,
            firstAirDate = firstAirDate?.let { mapTmdbLocalDate(it) },
            voteAverage = voteAverage
        )
    }
}