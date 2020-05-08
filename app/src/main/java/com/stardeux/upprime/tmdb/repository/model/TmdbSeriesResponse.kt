package com.stardeux.upprime.tmdb.repository.model

import com.google.gson.annotations.SerializedName

data class TmdbSeriesResponse(
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val posterUrl: String?
)