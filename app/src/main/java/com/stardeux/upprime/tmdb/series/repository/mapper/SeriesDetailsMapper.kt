package com.stardeux.upprime.tmdb.series.repository.mapper

import com.stardeux.upprime.database.parser.parseDatabaseGenres
import com.stardeux.upprime.database.parser.parseDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.common.request.TmdbSeriesRequest

class SeriesDetailsMapper(private val posterMapper: PosterMapper) {

    suspend fun mapToSeriesDetails(
        tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse, tmdbSeriesRequest: TmdbSeriesRequest
    ): SeriesDetails {
        return with(tmdbSeriesDetailsResponse) {
            SeriesDetails(
                tmdbId = requireNotNull(tmdbId),
                imdbId = tmdbSeriesRequest.imdbId,
                amazonId = tmdbSeriesRequest.amazonId,
                name = name,
                originalName = originalName,
                posterUrl = posterMapper.getCompletePosterUrl(posterUrl),
                mediaReleaseDate = mapTmdbLocalDate(firstAirDate),
                runtimeMinutes = episodeRuntime.firstOrNull { (it ?: 0) > 0 },
                genres = genres?.mapNotNull { it.name },
                nationalities = originCountry,
                tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
                amazonReleaseDate = tmdbSeriesRequest.amazonReleaseDate,
                synopsis = synopsis.takeIf { it?.isNotBlank() == true },
                backdropPath = posterMapper.getCompleteBackdropUrl(backdropPath)
            )
        }
    }


    fun mapToSeriesDetails(seriesDetailsEntity: SeriesDetailsEntity): SeriesDetails {
        return with(seriesDetailsEntity) {
            SeriesDetails(
                tmdbId = tmdbId,
                imdbId = imdbId,
                amazonId = amazonId,
                name = title,
                originalName = originalTitle,
                posterUrl = posterUrl,
                mediaReleaseDate = releaseDate,
                runtimeMinutes = runtime,
                genres = genres?.let(::parseDatabaseGenres),
                nationalities = productionCountries?.let(::parseDatabaseProductionCountries),
                tmdbRating = tmdbRating,
                amazonReleaseDate = amazonReleaseDate,
                synopsis = synopsis,
                backdropPath = backdropPath
            )
        }
    }

}