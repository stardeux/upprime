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
            getMovieDetailsUseCase.invoke(imdbId)
        } catch (exception: Exception) {
            searchMovie(imdbId, title) ?: throw MovieNotFoundException(imdbId)
        }
    }

    private suspend fun searchMovie(imdbId: String, title: String?): MovieDetails? {
        //If first request failed, i think that findMovieByImdbId will necessarily fail
        val tmdbId = findMovieUseCase.findMovieByImdbId(imdbId)?.tmdbId
        return if (tmdbId != null) {
            getMovieDetailsUseCase(tmdbId)
        } else {
            title?.let {
                val searchResult = searchMovieUseCase.searchMovie(title)
                if (searchResult.results.isNotEmpty()) {
                    getMovieDetailsUseCase(searchResult.results[0].tmdbId)
                } else {
                    null
                }
            }
        }
    }
}