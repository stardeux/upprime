package com.stardeux.upprime.tmdb.series.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToSeriesDetails(tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse, imdbId: String): SeriesDetails {
    return with(tmdbSeriesDetailsResponse) {
        SeriesDetails(
            tmdbId = requireNotNull(tmdbId),
            imdbId = imdbId,
            name = requireNotNull(name),
            posterUrl = requireNotNull(posterUrl),
            releaseDate = firstAirDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = episodeRuntime.getOrNull(0),
            genders = genres?.mapNotNull { it.name },
            nationalities = originCountry,
            averageRating = voteAverage
        )
    }
}