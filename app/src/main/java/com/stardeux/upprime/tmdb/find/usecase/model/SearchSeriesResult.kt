package com.stardeux.upprime.tmdb.find.usecase.model

class SearchSeriesResult(
    page: Int?,
    totalResults: Int?,
    totalPages: Int?,
    results: List<FindSeries>?
) : SearchResult<FindSeries>(page, totalResults, totalPages, results)