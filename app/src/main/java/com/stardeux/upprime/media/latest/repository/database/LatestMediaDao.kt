package com.stardeux.upprime.media.latest.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LatestMediaDao {

    @Query(value = "SELECT * FROM ${LatestMediaEntity.TABLE_LATEST_NAME} WHERE ${LatestMediaEntity.COL_ID} > :fromId ORDER BY ${LatestMediaEntity.COL_ID} LIMIT :limit")
    suspend fun getLatestMedia(fromId: Long, limit: Int): List<LatestMediaEntity>

    @Insert
    suspend fun insertAll(latestMedia: List<LatestMediaEntity>): List<Long>

    @Query("DELETE FROM ${LatestMediaEntity.TABLE_LATEST_NAME}")
    suspend fun clearTable()
}