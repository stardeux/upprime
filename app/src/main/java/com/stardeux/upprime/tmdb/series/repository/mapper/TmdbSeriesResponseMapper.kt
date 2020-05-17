package com.stardeux.upprime.tmdb.series.repository.mapper

import com.stardeux.upprime.database.parser.formatDatabaseGenres
import com.stardeux.upprime.database.parser.formatDatabaseLocalDate
import com.stardeux.upprime.database.parser.formatDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails


fun mapToSeriesDetailsEntity(seriesDetails: SeriesDetails): SeriesDetailsEntity {
    return with(seriesDetails) {
        SeriesDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = name,
            originalTitle = originalName,
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


