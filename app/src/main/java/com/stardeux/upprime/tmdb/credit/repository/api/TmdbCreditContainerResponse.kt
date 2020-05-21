package com.stardeux.upprime.tmdb.credit.repository.api

import com.google.gson.annotations.SerializedName

data class TmdbCreditContainerResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("cast") val cast: List<TmdbCastResponse>?,
    @SerializedName("crew") val crew: List<TmdbCrewResponse>?
)