package com.stardeux.upprime.tmdb.series.repository.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.stardeux.upprime.tmdb.common.model.database.*
import com.stardeux.upprime.tmdb.common.model.network.ProductionCountryResponse
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity

data class SeriesDetailsEntityWithGenreAndProductionCountry(

    @Embedded val series: SeriesDetailsEntity,

    @Relation(
        parentColumn = SeriesDetailsEntity.COL_SERIES_ID,
        entityColumn = GenreEntity.COL_GENRE_ID,
        associateBy = Junction(SeriesGenreCrossRef::class)
    )
    val genres: List<GenreEntity>?,


    @Relation(
        parentColumn = SeriesDetailsEntity.COL_SERIES_ID,
        entityColumn = ProductionCountryEntity.COL_PRODUCTION_COUNTRY_ID,
        associateBy = Junction(SeriesProductionCountryCrossRef::class)
    )
    val productionCountries: List<ProductionCountryResponse>?

)