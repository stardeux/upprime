package com.stardeux.upprime.media.latest.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LatestMediaDao {

    @Query(value = "SELECT * FROM ${LatestMediaEntity.TABLE_LATEST_NAME}")
    suspend fun getLatestMedia(): List<LatestMediaEntity>

    @Insert
    suspend fun insertAll(latestMedia: List<LatestMediaEntity>): List<Long>
}