package com.stardeux.upprime.media.expired.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpiredMediaDao {

    @Query(value = "SELECT * FROM ${ExpiredMediaEntity.TABLE_EXPIRED_NAME} WHERE ${ExpiredMediaEntity.COL_ID} > :fromId ORDER BY ${ExpiredMediaEntity.COL_ID} LIMIT :limit")
    suspend fun getExpiredMedia(fromId: Long, limit: Int): List<ExpiredMediaEntity>

    @Insert
    suspend fun insertAll(expiredMedia: List<ExpiredMediaEntity>): List<Long>

    @Query("DELETE FROM ${ExpiredMediaEntity.TABLE_EXPIRED_NAME}")
    suspend fun clearTable()

}