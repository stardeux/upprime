package com.stardeux.upprime.tmdb.find.usecase.model

open class SearchResult<T>(
    val page: Int, val totalResults: Int, val totalPages: Int, val results: List<T>
)
