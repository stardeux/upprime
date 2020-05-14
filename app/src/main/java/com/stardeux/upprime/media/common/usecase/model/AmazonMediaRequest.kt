package com.stardeux.upprime.media.common.usecase.model

data class AmazonMediaRequest(
    val country: String,
    val page: Int,
    val days: Int = 500
)