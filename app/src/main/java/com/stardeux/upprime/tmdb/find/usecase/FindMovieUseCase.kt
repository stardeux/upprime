package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.repository.mapper.mapToFindMovie
import com.stardeux.upprime.tmdb.find.usecase.model.FindMovie
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException

class FindMovieUseCase(private val findMediaRepository: FindMediaRepository) {

    suspend fun findMovieByImdbId(imdbId: String): FindMovie {
        val movieResult = findMediaRepository.findMedia(imdbId, "fr").moviesResults?.getOrNull(0)
        if (movieResult == null) {
            throw MovieNotFoundException(
                imdbId
            )
        } else {
            return mapToFindMovie(movieResult)
        }
    }
}