package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult

class SearchSeriesUseCase(private val searchSeriesRepository: SearchSeriesRepository) {

    suspend fun searchSeries(): SearchSeriesResult {

    }
}