package com.stardeux.upprime.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailDao
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDao
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity

@Database(
    entities = [SeriesDetailsEntity::class, MovieDetailsEntity::class],
    exportSchema = true,
    version = 1
)
abstract class UpPrimeDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailDao
    abstract fun seriesDetailsDao(): SeriesDao

    companion object {
        const val DB_NAME = "upprime.db"
    }
}