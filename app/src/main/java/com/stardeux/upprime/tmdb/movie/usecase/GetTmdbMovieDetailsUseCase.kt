package com.stardeux.upprime.tmdb.movie.usecase

import com.stardeux.upprime.tmdb.common.mapper.TmdbMediaRequestMapper
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.find.usecase.FindMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.SearchMovieUseCase
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails

class GetTmdbMovieDetailsUseCase(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val findMovieUseCase: FindMovieUseCase,
    private val searchMovieUseCase: SearchMovieUseCase,
    private val tmdbMediaRequestMapper: TmdbMediaRequestMapper
) {

    suspend operator fun invoke(imdbMediaRequest: ImdbMediaRequest): TmdbMovieDetails {
        return try {
            getMovieDetailsUseCase.invoke(
                tmdbMediaRequestMapper.mapToTmdbMovieRequest(imdbMediaRequest, null)
            )
        } catch (exception: Exception) {
            val movieDetails = searchMovie(imdbMediaRequest)
            return movieDetails ?: throw MovieNotFoundException(imdbMediaRequest.imdbId)
        }
    }

    private suspend fun searchMovie(imdbMediaRequest: ImdbMediaRequest): TmdbMovieDetails? {
        //If first request failed, i think that findMovieByImdbId will necessarily fail
        val tmdbId = findMovieUseCase.findMovieByImdbId(imdbMediaRequest.imdbId)?.tmdbId
        return if (tmdbId != null) {
            getMovieDetailsUseCase(
                tmdbMediaRequestMapper.mapToTmdbMovieRequest(
                    imdbMediaRequest, tmdbId
                )
            )
        } else {
            imdbMediaRequest.name?.let { name ->
                val searchResult = searchMovieUseCase.searchMovie(name)
                if (searchResult.results.isNotEmpty()) {
                    val matchingTitleIndex =
                        searchResult.results.indexOfFirst { it.title?.toLowerCase() == name.toLowerCase() }
                    val index = if (matchingTitleIndex == -1) 0 else matchingTitleIndex

                    getMovieDetailsUseCase(
                        tmdbMediaRequestMapper.mapToTmdbMovieRequest(
                            imdbMediaRequest, searchResult.results[index].tmdbId
                        )
                    )
                } else {
                    null
                }
            }
        }
    }
}