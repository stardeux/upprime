package com.stardeux.upprime.search.repository.api

import com.google.gson.annotations.SerializedName
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import org.threeten.bp.LocalDateTime

data class SearchMediaResponse(
    @SerializedName("Title") val title: String?,
    @SerializedName("Link") val amazonId: String,
    @SerializedName("imdbID") val imdbId: String,
    @SerializedName("DateAdded") val dateAdded: LocalDateTime?,
    @SerializedName("Year") val year: Int?,
    @SerializedName("Country") val country: String?,
    @SerializedName("Type") val type: MediaTypeResponse?
)