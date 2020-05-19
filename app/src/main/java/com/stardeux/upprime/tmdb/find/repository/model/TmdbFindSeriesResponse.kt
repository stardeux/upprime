package com.stardeux.upprime.tmdb.find.repository.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDate

data class TmdbFindSeriesResponse(
    @SerializedName("id") val tmdbId: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("first_air_date") val firstAirDate: LocalDate?,
    @SerializedName("vote_average") val voteAverage: Float?
)