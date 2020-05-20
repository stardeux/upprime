package com.stardeux.upprime.tmdb.video.repository.api

import com.google.gson.annotations.SerializedName

data class TmdbVideoResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("iso_639_1") val iso_639: String?,
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("site") val site: String?,
    @SerializedName("size") val size: Int?,
    @SerializedName("type") val type: VideoType?
)