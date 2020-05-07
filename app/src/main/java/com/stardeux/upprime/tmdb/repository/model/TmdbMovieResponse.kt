package com.stardeux.upprime.tmdb.repository.model

import com.google.gson.annotations.SerializedName

data class TmdbMovieResponse(
    @SerializedName("title")
    val title: String?
)