package com.stardeux.upprime.media.common.repository.api

import com.google.gson.annotations.SerializedName

enum class MediaTypeResponse {
    @SerializedName("Movie")
    MOVIE,

    @SerializedName("TV")
    SERIES
}