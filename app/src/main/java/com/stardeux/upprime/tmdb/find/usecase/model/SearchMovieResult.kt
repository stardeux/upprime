package com.stardeux.upprime.tmdb.find.usecase.model

class SearchMovieResult(
    page: Int,
    totalResults: Int,
    totalPages: Int,
    results: List<FindMovie>
) : SearchResult<FindMovie>(page, totalResults, totalPages, results)