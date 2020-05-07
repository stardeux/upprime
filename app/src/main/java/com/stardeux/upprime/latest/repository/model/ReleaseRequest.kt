package com.stardeux.upprime.latest.repository.model

data class ReleaseRequest(
    val country: String,
    val days: Int,
    val page: Int
)