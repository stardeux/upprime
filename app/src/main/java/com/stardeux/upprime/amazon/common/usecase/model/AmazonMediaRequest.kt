package com.stardeux.upprime.amazon.common.usecase.model

data class AmazonMediaRequest(
    val country: String,
    val days: Int,
    val page: Int
)