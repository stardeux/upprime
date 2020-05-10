package com.stardeux.upprime.amazon.common.usecase.model

data class AmazonMediaRequest(
    val country: String,
    val page: Int,
    val days: Int = 500
)