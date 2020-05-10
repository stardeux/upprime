package com.stardeux.upprime.amazon.latest.ui.mapper

import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.amazon.latest.ui.model.MediaUi
import com.stardeux.upprime.amazon.common.model.domain.Media
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

fun mapToMediaUi(media: Media): MediaUi {
    return with(media) {
        MediaUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = null,
            title = title,
            type = type,
            runtime = null,
            mainGenre = null,
            releaseDate = null,
            mainNationality = null,
            rating = null,
            posterUrl = null,
            amazonReleaseDate = dateAdded.toString()
        )
    }
}

fun mapToMediaUi(movieDetails: MovieDetails): MediaUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(movieDetails) {
        MediaUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            type = MediaType.MOVIE,
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genders?.getOrNull(0),
            releaseDate = mediaReleaseDate?.toString(),
            mainNationality = nationalities?.getOrNull(0),
            rating = averageRating,
            posterUrl = posterUrl,
            amazonReleaseDate = amazonReleaseDate.toString()
        )
    }
}

fun mapToMediaUi(seriesDetails: SeriesDetails): MediaUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(seriesDetails) {
        MediaUi(
            amazonId = amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = name,
            type = MediaType.SERIES,
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genders?.getOrNull(0),
            releaseDate = mediaReleaseDate?.toString(),
            mainNationality = nationalities?.getOrNull(0),
            rating = averageRating,
            posterUrl = posterUrl,
            amazonReleaseDate = amazonReleaseDate.toString()
        )
    }
}