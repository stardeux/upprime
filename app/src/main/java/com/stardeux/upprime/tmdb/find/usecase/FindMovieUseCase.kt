package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToFindMovie
import com.stardeux.upprime.tmdb.find.usecase.model.FindMovie
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException

class FindMovieUseCase(private val findMediaRepository: FindMediaRepository) {

    suspend fun findMovieByImdbId(imdbId: String): FindMovie? {
        return findMediaRepository.findMedia(imdbId, "fr").moviesResults?.getOrNull(0)?.let {
            mapToFindMovie(it)
        }
    }
}