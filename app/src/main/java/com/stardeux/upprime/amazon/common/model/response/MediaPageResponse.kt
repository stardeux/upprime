package com.stardeux.upprime.amazon.common.model.response

import com.google.gson.annotations.SerializedName

data class MediaPageResponse(
    @SerializedName("Count")
    val count: Int?,

    @SerializedName("Results")
    val results: List<MediaResponse>?
)