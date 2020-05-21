package com.stardeux.upprime.tmdb.credit.repository.api.model

import com.google.gson.annotations.SerializedName

data class TmdbCrewResponse(
    @SerializedName("credit_id") val creditId: String?,
    @SerializedName("department") val sound: String?,
    @SerializedName("gender") val gender: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("job") val job: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_path") val profilePath: String?
)