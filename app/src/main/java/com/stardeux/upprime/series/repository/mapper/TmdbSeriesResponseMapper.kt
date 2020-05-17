package com.stardeux.upprime.series.repository.mapper

import com.stardeux.upprime.database.parser.*
import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.series.usecase.model.SeriesDetails

fun mapToSeriesDetails(seriesDetailsEntity: SeriesDetailsEntity): SeriesDetails {
    return with(seriesDetailsEntity){
        SeriesDetails(
            tmdbId = tmdbId,
            imdbId = imdbId,
            amazonId = amazonId,
            name = title,
            originalName = originalTitle,
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


fun mapToSeriesDetails(
    tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse,
    tmdbSeriesRequest: TmdbSeriesRequest
): SeriesDetails {
    return with(tmdbSeriesDetailsResponse) {
        SeriesDetails(
            tmdbId = requireNotNull(tmdbId),
            imdbId = tmdbSeriesRequest.imdbId,
            amazonId = tmdbSeriesRequest.amazonId,
            name = name,
            originalName = originalName,
            posterUrl = requireNotNull(posterUrl),
            mediaReleaseDate = mapTmdbLocalDate(firstAirDate),
            runtimeMinutes = episodeRuntime.firstOrNull { (it ?: 0) > 0 },
            genres = genres?.mapNotNull { it.name },
            nationalities = originCountry,
            tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbSeriesRequest.amazonReleaseDate,
            synopsis = synopsis.takeIf { it?.isNotBlank() == true },
            backdropPath = backdropPath
        )
    }
}