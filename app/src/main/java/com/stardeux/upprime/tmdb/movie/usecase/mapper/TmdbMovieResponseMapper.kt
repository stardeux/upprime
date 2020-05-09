package com.stardeux.upprime.tmdb.movie.usecase.mapper

import com.stardeux.upprime.tmdb.common.mapper.mapTmdbLocalDate
import com.stardeux.upprime.tmdb.movie.repository.model.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun mapToMovieDetails(tmdbMovieDetailsResponse: TmdbMovieDetailsResponse): MovieDetails {
    return with(tmdbMovieDetailsResponse) {
        val detailsImdbId = requireNotNull(imdbId)
        val detailsTitle = requireNotNull(title)
        val detailsPosterUrl = requireNotNull(posterUrl)
        val detailsReleaseDate = releaseDate?.let { mapTmdbLocalDate(it) }
        val detailsRuntime = runtime
        val detailsGenres = genres?.mapNotNull { it.name }
        val detailsNationalities = productionCountries?.mapNotNull { it.name }
        val detailAverageRating = voteAverage

        MovieDetails(
            detailsImdbId,
            detailsTitle,
            detailsPosterUrl,
            detailsReleaseDate,
            detailsRuntime,
            detailsGenres,
            detailsNationalities,
            detailAverageRating
        )
    }
}