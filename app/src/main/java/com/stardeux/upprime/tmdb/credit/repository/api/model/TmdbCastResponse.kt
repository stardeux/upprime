package com.stardeux.upprime.tmdb.credit.repository.api.model

import com.google.gson.annotations.SerializedName

data class TmdbCastResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("cast_id") val castId: Int?,
    @SerializedName("character") val character: String?,
    @SerializedName("credit_id") val creditId: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
    @SerializedName("profile_path") val profilePath: String?
)