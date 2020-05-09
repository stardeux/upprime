package com.stardeux.upprime.tmdb.series.usecase

import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.error.SeriesNotFoundException
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetImdbSeriesDetailsUseCase(
    private val findSeriesUseCase: FindSeriesUseCase,
    private val searchSeriesUseCase: SearchSeriesUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) {

    suspend operator fun invoke(imdbId: String, name: String): SeriesDetails {
        val findResults = findSeriesUseCase.findSeriesByImdbId(imdbId) ?: searchSeries(name)
        val tmdbId = findResults?.tmdbId

        return tmdbId?.let {
            getSeriesDetailsUseCase(it)
        } ?: throw SeriesNotFoundException(imdbId)
    }

    private suspend fun searchSeries(name: String): FindSeries? {
        val searchResult = searchSeriesUseCase.searchSeries(name)
        return searchResult.results.find { it.name?.toLowerCase() == name.toLowerCase() }
    }

}