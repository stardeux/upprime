package com.stardeux.upprime.tmdb.common.model.network

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)