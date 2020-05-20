package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.SearchMovieRepository
import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchMoviesResult
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchSeriesResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchMovieResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult
import java.util.*

class SearchMovieUseCase(
    private val searchMovieRepository: SearchMovieRepository,
    private val locale: Locale
) {

    suspend fun searchMovie(query: String): SearchMovieResult {
        return mapToSearchMoviesResult(searchMovieRepository.searchMovie(query, locale.language))
    }
}