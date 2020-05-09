package com.stardeux.upprime.tmdb.find.repository.model

import com.google.gson.annotations.SerializedName

abstract class TmdbSearchResponse<T> {
    @SerializedName("page")
    val page: Int? = null
    @SerializedName("total_results")
    val totalResults: Int? = null
    @SerializedName("total_pages")
    val totalPages: Int? = null
    @SerializedName("results")
    val results: List<T>? = null
}