package com.stardeux.upprime.media.fiche.ui

import com.stardeux.upprime.core.mapper.mapReleaseDateToYear
import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.mapper.mapToHumanReadableMonthDay
import com.stardeux.upprime.core.mapper.mapToRatingString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

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
            runtime = runtimeMinutes?.let { mapRuntimeToString(runtimeMinutes) },
            mainGenre = genres?.getOrNull(0),
            mediaReleaseYear = mediaReleaseDate?.let { mapReleaseDateToYear(it) },
            mainNationality = nationalities?.getOrNull(0),
            rating = tmdbRating?.let { mapToRatingString(it) },
            posterUrl = posterUrl,
            amazonReleaseDate = mapToHumanReadableMonthDay(amazonReleaseDate),
            synopsis = synopsis,
            backdropPath = backdropPath
        )
    }
}