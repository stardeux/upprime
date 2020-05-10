package com.stardeux.upprime.amazon.common.model.request

data class AmazonMediaRequest(
    val country: String,
    val days: Int,
    val page: Int
)