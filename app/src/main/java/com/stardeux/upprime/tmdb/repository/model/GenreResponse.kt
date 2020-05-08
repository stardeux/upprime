package com.stardeux.upprime.tmdb.repository.model

import com.google.gson.annotations.SerializedName

class GenreResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?
)