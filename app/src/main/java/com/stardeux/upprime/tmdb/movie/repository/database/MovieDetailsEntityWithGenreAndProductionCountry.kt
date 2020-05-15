package com.stardeux.upprime.tmdb.movie.repository.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.stardeux.upprime.tmdb.common.model.database.GenreEntity
import com.stardeux.upprime.tmdb.common.model.database.MovieGenreCrossRef

data class MovieDetailsEntityWithGenreAndProductionCountry(

    @Embedded val movie: MovieDetailsEntity,

    @Relation(
        parentColumn = MovieDetailsEntity.COL_MOVIE_ID,
        entityColumn = GenreEntity.COL_GENRE_ID,
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<GenreEntity>

)