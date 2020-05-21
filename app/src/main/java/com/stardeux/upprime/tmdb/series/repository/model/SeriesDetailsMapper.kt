package com.stardeux.upprime.tmdb.series.repository.model

import com.stardeux.upprime.database.parser.parseDatabaseGenres
import com.stardeux.upprime.database.parser.parseDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest

class SeriesDetailsMapper {

    fun mapToSeriesDetails(
        tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse, tmdbSeriesRequest: TmdbSeriesRequest
    ): SeriesDetails {
        return with(tmdbSeriesDetailsResponse) {
            SeriesDetails(
                tmdbId = requireNotNull(tmdbId),
                imdbId = tmdbSeriesRequest.imdbId,
                amazonId = tmdbSeriesRequest.amazonId,
                name = name,
                originalName = originalName,
                posterUrl = posterUrl,
                mediaReleaseDate = firstAirDate,
                runtimeMinutes = episodeRuntime.firstOrNull { (it ?: 0) > 0 },
                genres = genres?.mapNotNull { it.name },
                nationalities = originCountry,
                tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
                amazonReleaseDate = tmdbSeriesRequest.amazonReleaseDate,
                synopsis = synopsis.takeIf { it?.isNotBlank() == true },
                backdropPath = backdropPath
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