package com.stardeux.upprime.media.latest.repository.database

import com.stardeux.upprime.core.extension.logDebug

class LatestMediaLocalDataSource(private val latestMediaDao: LatestMediaDao) {

    suspend fun getLatestMedia(fromId: Long, limit: Int): List<LatestMediaEntity> {
        logDebug("dao")
        return latestMediaDao.getLatestMedia(fromId, limit)
    }

    suspend fun insert(latestMedia: List<LatestMediaEntity>): List<Long> {
        logDebug("insert")
        return latestMediaDao.insertAll(latestMedia)
    }

    suspend fun clearTable() {
        logDebug("clear")
        latestMediaDao.clearTable()
    }
}