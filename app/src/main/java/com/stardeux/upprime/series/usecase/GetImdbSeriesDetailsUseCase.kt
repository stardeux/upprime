package com.stardeux.upprime.series.usecase

import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.common.request.mapToTmdbSeriesRequest
import com.stardeux.upprime.tmdb.find.usecase.FindSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchSeriesUseCase
import com.stardeux.upprime.tmdb.find.usecase.error.SeriesNotFoundException
import com.stardeux.upprime.tmdb.find.usecase.model.FindSeries
import com.stardeux.upprime.series.usecase.model.SeriesDetails

class GetImdbSeriesDetailsUseCase(
    private val findSeriesUseCase: FindSeriesUseCase,
    private val searchSeriesUseCase: SearchSeriesUseCase,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) {

    suspend operator fun invoke(imdbMediaRequest: ImdbMediaRequest): SeriesDetails {
        val findResults =
            findSeriesUseCase.findSeriesByImdbId(imdbMediaRequest.imdbId) ?: imdbMediaRequest.name?.let { searchSeries(it) }
        val tmdbId = findResults?.tmdbId

        return tmdbId?.let {
            getSeriesDetailsUseCase(mapToTmdbSeriesRequest(imdbMediaRequest, it))
        } ?: throw SeriesNotFoundException(imdbMediaRequest.imdbId)
    }

    private suspend fun searchSeries(name: String): FindSeries? {
        val searchResult = searchSeriesUseCase.searchSeries(name)
        return if (searchResult.results.isNotEmpty()) {
            val matchingTitleIndex =
                searchResult.results.indexOfFirst { it.name?.toLowerCase() == name.toLowerCase() }

            val index = if (matchingTitleIndex == -1) 0 else matchingTitleIndex
            searchResult.results[index]
        } else {
            null
        }

    }

}