package com.stardeux.upprime.tmdb.search_series.repository.model

import com.google.gson.annotations.SerializedName

data class TmdbSearchSeriesResponse(
    @SerializedName("page") val page: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    @SerializedName("total_pages") val totalPages: Int?
)