package com.stardeux.upprime.movie.repository.database

import com.stardeux.upprime.core.model.ImdbId

class MovieDetailLocalDataSource (private val movieDetailDao: MovieDetailDao) {

    suspend fun getMovieDetails(imdbId: ImdbId): MovieDetailsEntity? {
        return movieDetailDao.getMovieDetails(imdbId)
    }

    suspend fun insert(movieDetailsEntity: MovieDetailsEntity): Long {
        return movieDetailDao.insert(movieDetailsEntity)
    }

}