package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.SearchMovieRepository
import com.stardeux.upprime.tmdb.find.repository.SearchSeriesRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchMoviesResult
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToSearchSeriesResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchMovieResult
import com.stardeux.upprime.tmdb.find.usecase.model.SearchSeriesResult

class SearchMovieUseCase(private val searchMovieRepository: SearchMovieRepository) {

    suspend fun searchMovie(query: String): SearchMovieResult {
        return mapToSearchMoviesResult(searchMovieRepository.searchMovie(query, "fr"))
    }
}