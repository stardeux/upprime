package com.stardeux.upprime.amazon.common.model.request

data class ReleaseRequest(
    val country: String,
    val days: Int,
    val page: Int
)