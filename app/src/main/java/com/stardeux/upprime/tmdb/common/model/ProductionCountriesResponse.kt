package com.stardeux.upprime.tmdb.common.model

import com.google.gson.annotations.SerializedName

class ProductionCountriesResponse(
    @SerializedName("iso_3166_1") val isoCode: String?,
    @SerializedName("name") val name: String?
)