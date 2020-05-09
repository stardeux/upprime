package com.stardeux.upprime.tmdb.series.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.series.repository.model.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToSeriesDetails(tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse): SeriesDetails {
    return with(tmdbSeriesDetailsResponse) {
        SeriesDetails(
            imdbId = requireNotNull(imdbId),
            name = requireNotNull(name),
            posterUrl = requireNotNull(posterUrl),
            releaseDate = firstAirDate?.let { mapTmdbLocalDate(it) },
            runtimeMinutes = episodeRuntime,
            genders = genres?.mapNotNull { it.name },
            nationalities = originCountry,
            averageRating = voteAverage
        )
    }
}