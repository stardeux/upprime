package com.stardeux.upprime.media.latest.repository.database

class LatestMediaLocalDataSource(private val latestMediaDao: LatestMediaDao) {

    suspend fun getLatestMedia(fromId: Long, limit: Int): List<LatestMediaEntity> {
        return latestMediaDao.getLatestMedia(fromId, limit)
    }

    suspend fun insert(latestMedia: List<LatestMediaEntity>): List<Long> {
        return latestMediaDao.insertAll(latestMedia)
    }

    suspend fun clearTable() {
        latestMediaDao.clearTable()
    }
}