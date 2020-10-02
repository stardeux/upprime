package com.stardeux.upprime.tmdb.series.repository.model

import com.stardeux.upprime.database.parser.formatDatabaseGenres
import com.stardeux.upprime.database.parser.formatDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails


fun mapToSeriesDetailsEntity(tmdbSeriesDetails: TmdbSeriesDetails): SeriesDetailsEntity {
    return with(tmdbSeriesDetails) {
        SeriesDetailsEntity(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            title = name,
            originalTitle = originalName,
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


