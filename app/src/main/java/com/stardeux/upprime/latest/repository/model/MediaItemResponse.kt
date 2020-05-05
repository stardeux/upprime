package com.stardeux.upprime.latest.repository.model

import com.google.gson.annotations.SerializedName

data class MediaItemResponse(
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