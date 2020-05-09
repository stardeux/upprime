package com.stardeux.upprime.tmdb.find.usecase.mapper

import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchMovieResponse
import com.stardeux.upprime.tmdb.find.repository.model.TmdbSearchSeriesResponse
import com.stardeux.upprime.tmdb.find.usecase.model.SearchMovieResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult

fun mapToSearchSeriesResult(searchSeriesResponse: TmdbSearchSeriesResponse): SearchSeriesResult {
    return with(searchSeriesResponse) {
        SearchSeriesResult(
            page = page ?: 0,
            totalResults = totalResults ?: 0,
            totalPages = totalPages ?: 0,
            results = results?.map(::mapToFindSeries) ?: emptyList()
        )
    }
}

fun mapToSearchMoviesResult(searchSeriesResponse: TmdbSearchMovieResponse): SearchMovieResult {
    return with(searchSeriesResponse) {
        SearchMovieResult(
            page = page ?: 0,
            totalResults = totalResults ?: 0,
            totalPages = totalPages ?: 0,
            results = results?.map(::mapToFindMovie) ?: emptyList()
        )
    }
}