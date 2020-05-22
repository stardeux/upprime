package com.stardeux.upprime.search.repository.model

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

data class SearchMediaResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("ASIN") val amazonId: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("DateAdded") val dateAdded: LocalDateTime?,
    @SerializedName("Year") val year: Int?,
    @SerializedName("Country") val country: String?,
    @SerializedName("Type") val type: String?
)