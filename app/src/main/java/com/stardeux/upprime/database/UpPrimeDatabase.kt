package com.stardeux.upprime.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stardeux.upprime.media.common.repository.model.MediaTypeTypeConverter
import com.stardeux.upprime.media.latest.repository.database.LatestMediaDao
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailDao
import com.stardeux.upprime.tmdb.movie.repository.database.MovieDetailsEntity
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDao
import com.stardeux.upprime.tmdb.series.repository.database.SeriesDetailsEntity

@Database(
    entities = [SeriesDetailsEntity::class, MovieDetailsEntity::class, LatestMediaEntity::class],
    exportSchema = true,
    version = 1
)
@TypeConverters(MediaTypeTypeConverter::class)
abstract class UpPrimeDatabase : RoomDatabase() {

    abstract fun movieDetailsDao(): MovieDetailDao
    abstract fun seriesDetailsDao(): SeriesDao
    abstract fun latestMediaDao(): LatestMediaDao

    companion object {
        const val DB_NAME = "upprime.db"
    }
}