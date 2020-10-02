package com.stardeux.upprime.tmdb.series.repository.model

import com.stardeux.upprime.database.parser.parseDatabaseGenres
import com.stardeux.upprime.database.parser.parseDatabaseProductionCountries
import com.stardeux.upprime.tmdb.series.repository.api.TmdbSeriesDetailsResponse
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest

class SeriesDetailsMapper {

    fun mapToSeriesDetails(
        tmdbSeriesDetailsResponse: TmdbSeriesDetailsResponse, tmdbSeriesRequest: TmdbSeriesRequest
    ): TmdbSeriesDetails {
        return with(tmdbSeriesDetailsResponse) {
            TmdbSeriesDetails(
                tmdbId = requireNotNull(tmdbId),
                imdbId = tmdbSeriesRequest.imdbId,
                name = name,
                originalName = originalName,
                posterPath = posterPath,
                mediaReleaseDate = firstAirDate,
                runtimeMinutes = episodeRuntime.firstOrNull { (it ?: 0) > 0 },
                genres = genres?.mapNotNull { it.name },
                nationalities = originCountry,
                tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
                synopsis = synopsis.takeIf { it?.isNotBlank() == true },
                backdropPath = backdropPath
            )
        }
    }


    fun mapToSeriesDetails(seriesDetailsEntity: SeriesDetailsEntity): TmdbSeriesDetails {
        return with(seriesDetailsEntity) {
            TmdbSeriesDetails(
                tmdbId = tmdbId,
                imdbId = imdbId,
                name = title,
                originalName = originalTitle,
                posterPath = posterPath,
                mediaReleaseDate = releaseDate,
                runtimeMinutes = runtime,
                genres = genres?.let(::parseDatabaseGenres),
                nationalities = productionCountries?.let(::parseDatabaseProductionCountries),
                tmdbRating = tmdbRating,
                synopsis = synopsis,
                backdropPath = backdropPath
            )
        }
    }

}