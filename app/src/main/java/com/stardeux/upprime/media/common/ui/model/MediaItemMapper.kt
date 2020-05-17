package com.stardeux.upprime.media.common.ui.model

import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.usecase.model.Media
import com.stardeux.upprime.core.mapper.mapReleaseDateToYear
import com.stardeux.upprime.core.mapper.mapToHumanReadableMonthDay
import com.stardeux.upprime.core.mapper.mapToRatingString
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToMediaUi(
    media: Media, onFullCardClicked: (MediaItemUi) -> Unit
): MediaItemUi {
    return with(media) {
        MediaItemUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = null,
            title = title,
            type = type,
            runtime = null,
            mainGenre = null,
            mediaReleaseYear = null,
            mainNationality = null,
            rating = null,
            posterUrl = null,
            amazonReleaseDate = mapToHumanReadableMonthDay(dateAdded),
            synopsis = null,
            backdropPath = null,
            onCardClicked = onFullCardClicked
        )
    }
}

fun mapToMediaUi(
    movieDetails: MovieDetails,
    onFullCardClicked: (MediaItemUi) -> Unit
): MediaItemUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(movieDetails) {
        MediaItemUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            type = MediaType.MOVIE,
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genres?.getOrNull(0),
            mediaReleaseYear = mediaReleaseDate?.let { mapReleaseDateToYear(it) },
            mainNationality = nationalities?.getOrNull(0),
            rating = tmdbRating?.let { mapToRatingString(it) },
            posterUrl = posterUrl,
            amazonReleaseDate = mapToHumanReadableMonthDay(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath,
            onCardClicked = onFullCardClicked
        )
    }
}

fun mapToMediaUi(
    seriesDetails: SeriesDetails,
    onFullCardClicked: (MediaItemUi) -> Unit
): MediaItemUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(seriesDetails) {
        MediaItemUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = name,
            type = MediaType.SERIES,
            runtime = runtimeMinutes?.let { mapRuntimeToString(it) },
            mainGenre = genres?.getOrNull(0),
            mediaReleaseYear = mediaReleaseDate?.let { mapReleaseDateToYear(it) },
            mainNationality = nationalities?.getOrNull(0),
            rating = tmdbRating?.let { mapToRatingString(it) },
            posterUrl = posterUrl,
            amazonReleaseDate = mapToHumanReadableMonthDay(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath,
            onCardClicked = onFullCardClicked
        )
    }
}