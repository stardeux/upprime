package com.stardeux.upprime.latest.ui.mapper

import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media
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
            releaseYear = null,
            mainNationality = null,
            rating = null,
            posterUrl = null
        )
    }
}

fun mapToMediaUi(movieDetails: MovieDetails, shortMediaUi: MediaUi): MediaUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(movieDetails) {
        MediaUi(
            amazonId = shortMediaUi.amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = title,
            type = MediaType.MOVIE,
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genders?.getOrNull(0),
            releaseYear = releaseDate?.toString(),
            mainNationality = nationalities?.getOrNull(0),
            rating = averageRating,
            //TODO dynamically compute base url
            posterUrl = "https://image.tmdb.org/t/p/w500$posterUrl"
        )
    }
}

fun mapToMediaUi(seriesDetails: SeriesDetails, shortMediaUi: MediaUi): MediaUi {
    /**
     * Get ids from shortMediaUi because they are assuredly present
     */
    return with(seriesDetails) {
        MediaUi(
            amazonId = shortMediaUi.amazonId,
            imdbId = imdbId,
            tmdbId = tmdbId,
            title = name,
            type = MediaType.SERIES,
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genders?.getOrNull(0),
            releaseYear = releaseDate?.toString(),
            mainNationality = nationalities?.getOrNull(0),
            rating = averageRating,
            //TODO dynamically compute base url
            posterUrl = "https://image.tmdb.org/t/p/w500$posterUrl"
        )
    }
}