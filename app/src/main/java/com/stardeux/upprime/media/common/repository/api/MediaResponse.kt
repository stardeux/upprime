package com.stardeux.upprime.media.common.repository.api

import com.google.gson.annotations.SerializedName
import org.threeten.bp.LocalDateTime

data class MediaResponse(
    @SerializedName("Title")
    val title : String?,

    @SerializedName("ASIN")
    val amazonId: String?,

    @SerializedName("imdbID")
    val imdbId: String?,

    @SerializedName("DateAdded")
    val dateAdded: LocalDateTime?,

    @SerializedName("Type")
    val type: String?
)