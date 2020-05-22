package com.stardeux.upprime.search.repository.api

import com.google.gson.annotations.SerializedName

data class SearchMediaContainerResponse(
    @SerializedName("Count") val count: Int?,
    @SerializedName("Results") val results: List<SearchMediaResponse>?
)