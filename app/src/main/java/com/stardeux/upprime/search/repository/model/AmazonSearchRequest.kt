package com.stardeux.upprime.search.repository.model

data class AmazonSearchRequest(
    val title: String,
    val yearStart: Int,
    val yearEnd: Int,
    val page: Int
)