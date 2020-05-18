package com.stardeux.upprime.media.common.repository.model

import com.google.gson.annotations.SerializedName

data class MediaPageResponse(
    @SerializedName("Count")
    val count: Int?,

    @SerializedName("Results")
    val results: List<MediaResponse>?
) {
    companion object {
        const val PAGE_SIZE = 100
    }
}