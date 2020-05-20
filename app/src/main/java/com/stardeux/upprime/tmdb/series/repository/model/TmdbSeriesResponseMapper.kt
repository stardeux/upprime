package com.stardeux.upprime.tmdb.series.repository.model

import com.stardeux.upprime.database.parser.formatDatabaseGenres
import com.stardeux.upprime.database.parser.formatDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity


fun mapToSeriesDetailsEntity(seriesDetails: SeriesDetails): SeriesDetailsEntity {
    return with(seriesDetails) {
        SeriesDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = name,
            originalTitle = originalName,
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


