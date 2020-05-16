package com.stardeux.upprime.tmdb.movie.repository

import com.stardeux.upprime.tmdb.common.request.TmdbMovieRequest
import com.stardeux.upprime.tmdb.movie.repository.api.MovieDetailsRemoteDataSource
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailLocalDataSource
import com.stardeux.upprime.tmdb.movie.usecase.mapper.mapToMovieDetails
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

class MovieRepository(
    private val movieDetailsRemoteDataSource: MovieDetailsRemoteDataSource,
    private val movieDetailLocalDataSource: MovieDetailLocalDataSource
) {

    suspend fun getMovieDetails(
        tmdbMovieRequest: TmdbMovieRequest, language: String
    ): MovieDetails {
        return mapToMovieDetails(movieDetailsRemoteDataSource.getMovieDetails(tmdbMovieRequest.imdbId, language), tmdbMovieRequest)
    }
}