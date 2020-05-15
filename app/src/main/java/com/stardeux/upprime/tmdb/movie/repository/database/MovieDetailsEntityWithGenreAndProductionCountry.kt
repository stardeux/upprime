package com.stardeux.upprime.tmdb.movie.repository.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.stardeux.upprime.tmdb.common.model.database.GenreEntity
import com.stardeux.upprime.tmdb.common.model.database.MovieGenreCrossRef
import com.stardeux.upprime.tmdb.common.model.database.MovieProductionCountryCrossRef
import com.stardeux.upprime.tmdb.common.model.database.ProductionCountryEntity
import com.stardeux.upprime.tmdb.common.model.network.ProductionCountryResponse

data class MovieDetailsEntityWithGenreAndProductionCountry(

    @Embedded val movie: MovieDetailsEntity,

    @Relation(
        parentColumn = MovieDetailsEntity.COL_MOVIE_ID,
        entityColumn = GenreEntity.COL_GENRE_ID,
        associateBy = Junction(MovieGenreCrossRef::class)
    )
    val genres: List<GenreEntity>?,


    @Relation(
        parentColumn = MovieDetailsEntity.COL_MOVIE_ID,
        entityColumn = ProductionCountryEntity.COL_PRODUCTION_COUNTRY_ID,
        associateBy = Junction(MovieProductionCountryCrossRef::class)
    )
    val productionCountries: List<ProductionCountryResponse>?

)