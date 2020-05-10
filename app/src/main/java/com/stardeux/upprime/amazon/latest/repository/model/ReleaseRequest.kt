package com.stardeux.upprime.amazon.latest.repository.model

data class ReleaseRequest(
    val country: String,
    val days: Int,
    val page: Int
)