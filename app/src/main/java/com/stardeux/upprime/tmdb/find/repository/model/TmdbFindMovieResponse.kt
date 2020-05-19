package com.stardeux.upprime.tmdb.find.repository.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class TmdbFindMovieResponse(
    @SerializedName("id") val tmdbId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("release_date") val releaseDate: LocalDate?,
    @SerializedName("vote_average") val voteAverage: Float?
)