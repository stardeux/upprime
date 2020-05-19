package com.stardeux.upprime.media.common.repository.api

import com.google.gson.annotations.SerializedName

data class MediaResponse(
    @SerializedName("Title")
    val title : String?,

    @SerializedName("ASIN")
    val amazonId: String?,

    @SerializedName("imdbID")
    val imdbId: String?,

    @SerializedName("DateAdded")
    val dateAdded: String?,

    @SerializedName("Type")
    val type: String?
)