package com.stardeux.upprime.media.fiche.ui.mapper

import com.stardeux.upprime.core.mapper.formatToHumanReadableMonthDay
import com.stardeux.upprime.core.mapper.mapReleaseDateToYear
import com.stardeux.upprime.core.mapper.mapRuntimeToString
import com.stardeux.upprime.core.mapper.mapToRatingString
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.media.fiche.usecase.GetPlayAmazonVideoIntentUseCase
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails

class MediaFicheUiMapper(
    private val posterMapper: PosterMapper
) {

    suspend fun mapToMediaFicheUi(
        shortMedia: ShortMedia,
        tmdbMovieDetails: TmdbMovieDetails
    ): MediaFicheUi {
        return with(tmdbMovieDetails) {
            MediaFicheUi(
                amazonId = shortMedia.amazonId,
                imdbId = imdbId,
                tmdbId = tmdbId,
                title = title,
                type = MediaType.MOVIE,
                runtime = mapRuntimeToString(runtimeMinutes),
                genres = genres ?: emptyList(),
                mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
                mainNationality = nationalities?.getOrNull(0),
                tmdbRating = mapToRatingString(tmdbRating),
                posterUrl = posterMapper.getCompletePosterUrl(posterPath),
                amazonReleaseDate = formatToHumanReadableMonthDay(shortMedia.dateAdded),
                synopsis = synopsis,
                backdropUrl = posterMapper.getCompleteBackdropUrl(backdropPath),
                amazonWebUrl = shortMedia.amazonWebUrl
            )
        }
    }

    suspend fun mapToMediaFicheUi(
        shortMedia: ShortMedia,
        tmdbSeriesDetails: TmdbSeriesDetails
    ): MediaFicheUi {
        return with(tmdbSeriesDetails) {
            MediaFicheUi(
                amazonId = shortMedia.amazonId,
                imdbId = imdbId,
                tmdbId = tmdbId,
                title = name,
                type = MediaType.MOVIE,
                runtime = mapRuntimeToString(runtimeMinutes),
                genres = genres ?: emptyList(),
                mediaReleaseYear = mapReleaseDateToYear(mediaReleaseDate),
                mainNationality = nationalities?.getOrNull(0),
                tmdbRating = mapToRatingString(tmdbRating),
                posterUrl = posterMapper.getCompletePosterUrl(posterPath),
                amazonReleaseDate = formatToHumanReadableMonthDay(shortMedia.dateAdded),
                synopsis = synopsis,
                backdropUrl = posterMapper.getCompleteBackdropUrl(backdropPath),
                amazonWebUrl = shortMedia.amazonWebUrl
            )
        }
    }
}