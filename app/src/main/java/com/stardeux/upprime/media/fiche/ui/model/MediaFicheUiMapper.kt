package com.stardeux.upprime.media.fiche.ui.model

import com.stardeux.upprime.core.mapper.mapReleaseDateToYear
import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.mapper.formatToHumanReadableMonthDay
import com.stardeux.upprime.core.mapper.mapToRatingString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToMediaFicheUi(
    movieDetails: MovieDetails
): MediaFicheUi {
    return with(movieDetails) {
        MediaFicheUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            type = MediaType.MOVIE,
            runtime = mapRuntimeToString(runtimeMinutes),
            genres = genres,
            mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
            mainNationality = nationalities?.getOrNull(0),
            tmdbRating = mapToRatingString(tmdbRating),
            posterUrl = posterUrl,
            amazonReleaseDate = formatToHumanReadableMonthDay(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}

fun mapToMediaFicheUi(
    seriesDetails: SeriesDetails
): MediaFicheUi {
    return with(seriesDetails) {
        MediaFicheUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = name,
            type = MediaType.MOVIE,
            runtime = mapRuntimeToString(runtimeMinutes),
            genres = genres,
            mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
            mainNationality = nationalities?.getOrNull(0),
            tmdbRating = mapToRatingString(tmdbRating),
            posterUrl = posterUrl,
            amazonReleaseDate = formatToHumanReadableMonthDay(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}