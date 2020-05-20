package com.stardeux.upprime.tmdb.find.usecase

import com.stardeux.upprime.tmdb.find.repository.FindMediaRepository
import com.stardeux.upprime.tmdb.find.usecase.mapper.mapToFindMovie
import com.stardeux.upprime.tmdb.find.usecase.model.FindMovie
import com.stardeux.upprime.tmdb.find.usecase.error.MovieNotFoundException
import java.util.*

class FindMovieUseCase(
    private val findMediaRepository: FindMediaRepository,
    private val locale: Locale
) {

    suspend fun findMovieByImdbId(imdbId: String): FindMovie? {
        return findMediaRepository.findMedia(imdbId, locale.language).moviesResults?.getOrNull(0)?.let {
            mapToFindMovie(it)
        }
    }
}