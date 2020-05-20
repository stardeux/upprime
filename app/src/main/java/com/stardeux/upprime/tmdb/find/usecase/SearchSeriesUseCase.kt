package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchSeriesResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult
import java.util.*

class SearchSeriesUseCase(
    private val searchSeriesRepository: SearchSeriesRepository,
    private val locale: Locale
) {

    suspend fun searchSeries(query: String): SearchSeriesResult {
        return mapToSearchSeriesResult(searchSeriesRepository.searchSeries(query, locale.language))
    }
}