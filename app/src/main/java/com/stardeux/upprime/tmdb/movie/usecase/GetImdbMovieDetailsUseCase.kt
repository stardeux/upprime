package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class GetImdbMovieDetailsUseCase(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val findMovieUseCase: FindMovieUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) {

    suspend operator fun invoke(imdbId: String, title: String?): MovieDetails {
        return try {
            getMovieDetailsUseCase.invoke(imdbId, null)
        } catch (exception: Exception) {
            val a = searchMovie(imdbId, title)
            return a ?: throw MovieNotFoundException(imdbId)
        }
    }

    private suspend fun searchMovie(imdbId: String, title: String?): MovieDetails? {
        //If first request failed, i think that findMovieByImdbId will necessarily fail
        val tmdbId = findMovieUseCase.findMovieByImdbId(imdbId)?.tmdbId
        return if (tmdbId != null) {
            getMovieDetailsUseCase(tmdbId, tmdbId)
        } else {
            title?.let {
                val searchResult = searchMovieUseCase.searchMovie(title)
                if (searchResult.results.isNotEmpty()) {
                    val matchingTitleIndex = searchResult.results.indexOfFirst { it.title?.toLowerCase() == title.toLowerCase() }
                    val index = if (matchingTitleIndex == -1) 0 else matchingTitleIndex
                    getMovieDetailsUseCase(imdbId, searchResult.results[index].tmdbId)
                } else {
                    null
                }
            }
        }
    }
}