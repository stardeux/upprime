package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailLocalDataSource
import com.stardeux.upprime.tmdb.movie.repository.model.MovieDetailsMapper
import com.stardeux.upprime.tmdb.movie.repository.model.mapToMovieDetailsEntity
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails

class MovieRepository(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource,
    private val movieDetailsMapper: MovieDetailsMapper
) {

    suspend fun getMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest, language: String
    ): TmdbMovieDetails {
        val cached = getCachedMovieDetails(tmdbMovieRequest.imdbId)
        return cached ?: getRemoteMovieDetails(tmdbMovieRequest, language)
            .also { cacheMovieDetails(it) }
    }

    suspend fun cacheMovieDetails(tmdbMovieDetails: TmdbMovieDetails) {
        movieDetailLocalDataSource.insert(mapToMovieDetailsEntity(tmdbMovieDetails))
    }

    suspend fun getCachedMovieDetails(imdbId: ImdbId): TmdbMovieDetails? {
        return movieDetailLocalDataSource.getMovieDetails(imdbId)
            ?.let { movieDetailsMapper.mapToMovieDetails(it) }
    }

    suspend fun getRemoteMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest,
        language: String
    ): TmdbMovieDetails {
        val movieId = tmdbMovieRequest.tmdbId ?: tmdbMovieRequest.imdbId
        return movieDetailsMapper.mapToMovieDetails(
            movieDetailsRemoteDataSource.getMovieDetails(movieId, language),
            tmdbMovieRequest
        )
    }
}