package com.stardeux.upprime.tmdb.series.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId

@Dao
interface SeriesDao {

    @Query(value = "SELECT * FROM ${SeriesDetailsEntity.TABLE_SERIES_NAME} WHERE ${SeriesDetailsEntity.COL_SERIES_IMDB_ID} = :imdbId")
    suspend fun getSeriesByImdbId(imdbId: ImdbId): SeriesDetailsEntity?

    @Insert
    suspend fun insert(seriesDetailsEntity: SeriesDetailsEntity): Long

}