package com.stardeux.upprime.tmdb.movie.repository.model

import com.stardeux.upprime.database.parser.parseDatabaseGenres
import com.stardeux.upprime.database.parser.parseDatabaseProductionCountries
import com.stardeux.upprime.tmdb.movie.repository.api.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.common.mapper.PosterMapper
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieRequest

class MovieDetailsMapper (private val posterMapper: PosterMapper) {

    fun mapToMovieDetails(movieDetailsEntity: MovieDetailsEntity): MovieDetails {
        return with(movieDetailsEntity){
            MovieDetails(
                tmdbId = tmdbId,
                imdbId = imdbId,
                amazonId = amazonId,
                title = title,
                originalTitle = originalTitle,
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

    suspend fun mapToMovieDetails(
        tmdbMovieDetailsResponse: TmdbMovieDetailsResponse, tmdbMovieRequest: TmdbMovieRequest
    ): MovieDetails {
        return with(tmdbMovieDetailsResponse) {
            MovieDetails(
                tmdbId = requireNotNull(tmdbId),
                imdbId = tmdbMovieRequest.imdbId,
                amazonId = tmdbMovieRequest.amazonId,
                title = title,
                originalTitle = originalTitle,
                posterUrl = posterMapper.getCompletePosterUrl(posterUrl),
                mediaReleaseDate = releaseDate,
                runtimeMinutes = runtime?.takeIf { it > 0 },
                genres = genres?.mapNotNull { it.name },
                nationalities = productionCountries?.mapNotNull { it.name },
                tmdbRating = voteAverage?.takeIf { voteCount ?: 0 > 0 },
                amazonReleaseDate = tmdbMovieRequest.amazonReleaseDate,
                synopsis = synopsis.takeIf { it?.isNotBlank() == true },
                backdropPath = posterMapper.getCompleteBackdropUrl(backdropPath)
            )
        }
    }

}