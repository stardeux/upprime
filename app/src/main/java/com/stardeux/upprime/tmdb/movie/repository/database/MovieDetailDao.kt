package com.stardeux.upprime.tmdb.movie.repository.database

import androidx.room.Dao
import androidx.room.Query
import com.stardeux.upprime.core.model.ImdbId

@Dao
interface MovieDetailDao {

    @Query(value = "SELECT * FROM ${MovieDetailsEntity.TABLE_MOVIE_NAME} WHERE ${MovieDetailsEntity.COL_MOVIE_IMDB_ID} = :imdbId")
    suspend fun getMovieDetails(imdbId: ImdbId): MovieDetailsEntityWithGenreAndProductionCountry

    suspend fun insert(movieDetailsEntity: MovieDetailsEntityWithGenreAndProductionCountry): Long
}