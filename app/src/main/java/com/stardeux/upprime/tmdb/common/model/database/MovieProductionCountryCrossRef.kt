package com.stardeux.upprime.tmdb.common.model.database

import androidx.room.Entity
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity

@Entity(primaryKeys = [MovieDetailsEntity.COL_MOVIE_ID, ProductionCountryEntity.COL_PRODUCTION_COUNTRY_ID])
data class MovieProductionCountryCrossRef(
    val movieId: Long, val productionCountryId: Long
)
