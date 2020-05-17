package com.stardeux.upprime.movie.repository

import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.movie.repository.database.MovieDetailLocalDataSource
import com.stardeux.upprime.movie.repository.mapper.MovieDetailsMapper
import com.stardeux.upprime.movie.repository.mapper.mapToMovieDetailsEntity
import com.stardeux.upprime.movie.usecase.model.MovieDetails

class MovieRepository(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource,
    private val movieDetailsMapper: MovieDetailsMapper
) {

    suspend fun getMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest, language: String
    ): MovieDetails {
        val cached = movieDetailLocalDataSource.getMovieDetails(tmdbMovieRequest.imdbId)
        return if (cached != null) {
            movieDetailsMapper.mapToMovieDetails(cached)
        } else {
            movieDetailsMapper.mapToMovieDetails(
                movieDetailsRemoteDataSource.getMovieDetails(tmdbMovieRequest.imdbId, language),
                tmdbMovieRequest
            ).also { movieDetailLocalDataSource.insert(mapToMovieDetailsEntity(it)) }
        }
    }
}