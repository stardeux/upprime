package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.Entity
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity

@Entity(primaryKeys = [MovieDetailsEntity.COL_MOVIE_ID, GenreEntity.COL_GENRE_ID])
data class MovieGenreCrossRef(
    val movieId: Long,
    val genreId: Long
)
