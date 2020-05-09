package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchSeriesResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult

class SearchSeriesUseCase(private val searchSeriesRepository: SearchSeriesRepository) {

    suspend fun searchSeries(query: String): SearchSeriesResult {
        return mapToSearchSeriesResult(searchSeriesRepository.searchSeries(query, "fr"))
    }
}