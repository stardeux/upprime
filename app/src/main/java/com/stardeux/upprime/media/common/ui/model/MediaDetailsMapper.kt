package com.stardeux.upprime.media.common.ui.model

import com.stardeux.upprime.core.mapper.formatToHumanReadableMonthDay
import com.stardeux.upprime.core.mapper.mapReleaseDateToYear
import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.mapper.mapToRatingString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class MediaDetailsMapper (private val posterMapper: PosterMapper) {

    fun mapToMediaUi(
        shortMedia: ShortMedia, onFullCardClicked: (MediaItemUi) -> Unit
    ): MediaItemUi {
        return with(shortMedia) {
            MediaItemUi(
                shortMedia = shortMedia,
                tmdbId = null,
                title = title,
                type = type,
                runtime = null,
                mainGenre = null,
                mediaReleaseYear = null,
                mainNationality = null,
                rating = null,
                posterUrl = null,
                amazonReleaseDate = formatToHumanReadableMonthDay(dateAdded),
                synopsis = null,
                backdropUrl = null,
                onCardClicked = onFullCardClicked
            )
        }
    }

    suspend fun mapToMediaUi(
        shortMedia: ShortMedia,
        movieDetails: MovieDetails,
        onFullCardClicked: (MediaItemUi) -> Unit
    ): MediaItemUi {
        /**
         * Get ids from shortMediaUi because they are assuredly present
         */
        return with(movieDetails) {
            MediaItemUi(
                shortMedia = shortMedia,
                tmdbId = tmdbId,
                title = title,
                type = MediaType.MOVIE,
                runtime = mapRuntimeToString(runtimeMinutes),
                mainGenre = genres?.getOrNull(0),
                mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
                mainNationality = nationalities?.getOrNull(0),
                rating = mapToRatingString(tmdbRating),
                posterUrl = posterMapper.getCompletePosterUrl(posterPath),
                amazonReleaseDate = formatToHumanReadableMonthDay(amazonReleaseDate),
                synopsis = synopsis,
                backdropUrl = posterMapper.getCompleteBackdropUrl(backdropPath),
                onCardClicked = onFullCardClicked
            )
        }
    }

    suspend fun mapToMediaUi(
        shortMedia: ShortMedia,
        seriesDetails: SeriesDetails,
        onFullCardClicked: (MediaItemUi) -> Unit
    ): MediaItemUi {
        /**
         * Get ids from shortMediaUi because they are assuredly present
         */
        return with(seriesDetails) {
            MediaItemUi(
                shortMedia = shortMedia,
                tmdbId = tmdbId,
                title = name,
                type = MediaType.SERIES,
                runtime = mapRuntimeToString(runtimeMinutes),
                mainGenre = genres?.getOrNull(0),
                mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
                mainNationality = nationalities?.getOrNull(0),
                rating = mapToRatingString(tmdbRating),
                posterUrl = posterMapper.getCompletePosterUrl(posterPath),
                amazonReleaseDate = formatToHumanReadableMonthDay(amazonReleaseDate),
                synopsis = synopsis,
                backdropUrl = posterMapper.getCompleteBackdropUrl(backdropPath),
                onCardClicked = onFullCardClicked
            )
        }
    }

}