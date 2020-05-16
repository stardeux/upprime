package com.stardeux.upprime.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stardeux.upprime.tmdb.common.model.database.*

@Database(
    entities = [
        GenreEntity::class,
        MovieGenreCrossRef::class,
        MovieProductionCountryCrossRef::class,
        ProductionCountryEntity::class,
        SeriesGenreCrossRef::class,
        SeriesProductionCountryCrossRef::class],
    exportSchema = true,
    version = 1
)
abstract class UpPrimeDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "upprime.db"
    }
}