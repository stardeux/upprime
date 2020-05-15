package com.stardeux.upprime.tmdb.series.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToSeriesDetails(
    tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse,
    tmdbSeriesRequest: TmdbSeriesRequest
): SeriesDetails {
    return with(tmdbSeriesDetailsResponse) {
        SeriesDetails(
            tmdbId = requireNotNull(tmdbId),
            imdbId = tmdbSeriesRequest.imdbId,
            amazonId = tmdbSeriesRequest.amazonId,
            name = requireNotNull(name),
            posterUrl = requireNotNull(posterUrl),
            mediaReleaseDate = firstAirDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = episodeRuntime.firstOrNull { (it ?: 0) > 0 },
            genders = genres?.mapNotNull { it.name },
            nationalities = originCountry,
            averageRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
            amazonReleaseDate = tmdbSeriesRequest.amazonReleaseDate,
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}