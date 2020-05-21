package com.stardeux.upprime.tmdb.video.repository.api.model

import com.google.gson.annotations.SerializedName

data class TmdbVideoContainerResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("results") val results: List<TmdbVideoResponse>?
)