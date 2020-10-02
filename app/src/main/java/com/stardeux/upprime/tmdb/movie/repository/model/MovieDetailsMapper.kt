package com.stardeux.upprime.tmdb.movie.repository.model

import com.stardeux.upprime.database.parser.parseDatabaseGenres
import com.stardeux.upprime.database.parser.parseDatabaseProductionCountries
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieRequest

class MovieDetailsMapper {

    fun mapToMovieDetails(movieDetailsEntity: MovieDetailsEntity): TmdbMovieDetails {
        return with(movieDetailsEntity){
            TmdbMovieDetails(
                tmdbId = tmdbId,
                imdbId = imdbId,
                amazonId = amazonId,
                title = title,
                originalTitle = originalTitle,
                posterPath = posterPath,
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

    suspend fun mapToMovieDetails(
        tmdbMovieDetailsResponse: TmdbMovieDetailsResponse, tmdbMovieRequest: TmdbMovieRequest
    ): TmdbMovieDetails {
        return with(tmdbMovieDetailsResponse) {
            TmdbMovieDetails(
                tmdbId = requireNotNull(tmdbId),
                imdbId = tmdbMovieRequest.imdbId,
                amazonId = tmdbMovieRequest.amazonId,
                title = title,
                originalTitle = originalTitle,
                posterPath = posterPath,
                mediaReleaseDate = releaseDate,
                runtimeMinutes = runtime?.takeIf { it > 0 },
                genres = genres?.mapNotNull { it.name },
                nationalities = productionCountries?.mapNotNull { it.name },
                tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
                amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate,
                synopsis = synopsis.takeIf { it?.isNotBlank() == true },
                backdropPath = backdropPath
            )
        }
    }

}