package com.stardeux.upprime.media.expired.repository.database

import com.stardeux.upprime.core.extension.logDebug

class ExpiredMediaLocalDataSource(private val expiredMediaDao: ExpiredMediaDao) {

    suspend fun getExpiredMedia(fromId: Long, limit: Int): List<ExpiredMediaEntity> {
        logDebug("dao $fromId to $limit")
        return expiredMediaDao.getExpiredMedia(fromId, limit)
    }

    suspend fun insert(expiredMedia: List<ExpiredMediaEntity>): List<Long> {
        logDebug("insert")
        return expiredMediaDao.insertAll(expiredMedia)
    }

    suspend fun clearTable() {
        logDebug("clear")
        expiredMediaDao.clearTable()
    }
}