package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailLocalDataSource
import com.stardeux.upprime.tmdb.movie.repository.mapper.MovieDetailsMapper
import com.stardeux.upprime.tmdb.movie.repository.mapper.mapToMovieDetailsEntity
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class MovieRepository(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource,
    private val movieDetailsMapper: MovieDetailsMapper
) {

    suspend fun getMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest, language: String
    ): MovieDetails {
        val cached = getCachedMovieDetails(tmdbMovieRequest.imdbId)
        return cached ?: getRemoteMovieDetails(tmdbMovieRequest, language)
            .also { cacheMovieDetails(it) }
    }

    suspend fun cacheMovieDetails(movieDetails: MovieDetails) {
        movieDetailLocalDataSource.insert(mapToMovieDetailsEntity(movieDetails))
    }

    suspend fun getCachedMovieDetails(imdbId: ImdbId): MovieDetails? {
        return movieDetailLocalDataSource.getMovieDetails(imdbId)
            ?.let { movieDetailsMapper.mapToMovieDetails(it) }
    }

    suspend fun getRemoteMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest,
        language: String
    ): MovieDetails {
        val movieId = tmdbMovieRequest.tmdbId ?: tmdbMovieRequest.imdbId
        return movieDetailsMapper.mapToMovieDetails(
            movieDetailsRemoteDataSource.getMovieDetails(movieId, language),
            tmdbMovieRequest
        )
    }
}