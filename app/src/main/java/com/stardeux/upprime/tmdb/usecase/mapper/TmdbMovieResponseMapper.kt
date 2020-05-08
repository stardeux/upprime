package com.stardeux.upprime.tmdb.usecase.mapper

import com.stardeux.upprime.tmdb.repository.model.TmdbMovieDetailsResponse
import com.stardeux.upprime.tmdb.usecase.model.MovieDetails
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun mapToMovieDetails(tmdbMovieDetailsResponse: TmdbMovieDetailsResponse): MovieDetails {
    return with(tmdbMovieDetailsResponse) {
        val detailsImdbId = requireNotNull(imdbId)
        val detailsTitle = requireNotNull(title)
        val detailsPosterUrl = requireNotNull(posterUrl)
        val detailsReleaseDate = releaseDate?.let {
            DateTimeFormatter.ISO_LOCAL_DATE.parse(
                it, LocalDate::from
            )
        }
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