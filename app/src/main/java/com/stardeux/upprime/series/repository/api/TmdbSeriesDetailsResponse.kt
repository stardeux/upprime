package com.stardeux.upprime.series.repository.api

import com.google.gson.annotations.SerializedName
import com.stardeux.upprime.tmdb.common.model.network.GenreResponse

data class TmdbSeriesDetailsResponse(
    @SerializedName("id") val tmdbId: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("episode_run_time") val episodeRuntime: List<Int?>,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("origin_country") val originCountry: List<String>?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("overview") val synopsis: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)