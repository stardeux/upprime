package com.stardeux.upprime.tmdb.configuration.repository.model

import com.google.gson.annotations.SerializedName

data class TmdbConfigurationResponse(
    @SerializedName("images") val image: TmdbImageConfigurationResponse?
)