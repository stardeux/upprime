package com.stardeux.upprime.tmdb.series.repository.model

import com.google.gson.annotations.SerializedName
import com.stardeux.upprime.tmdb.common.model.GenreResponse

data class TmdbSeriesResponse(
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("episode_run_time") val episodeRuntime: Int?,
    @SerializedName("genres") val genres: List<GenreResponse>?,
    @SerializedName("origin_country") val originCountry: String?,
    @SerializedName("vote_average") val voteAverage: Float?
)