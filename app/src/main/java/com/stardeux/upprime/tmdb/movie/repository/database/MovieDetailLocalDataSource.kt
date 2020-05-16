package com.stardeux.upprime.tmdb.movie.repository.database

class MovieDetailLocalDataSource (private val movieDetailDao: MovieDetailDao) {

    suspend fun getMovieDetails(): MovieDetailsEntityWithGenreAndProductionCountry {
        return movieDetailDao.getMovieDetails()
    }

}