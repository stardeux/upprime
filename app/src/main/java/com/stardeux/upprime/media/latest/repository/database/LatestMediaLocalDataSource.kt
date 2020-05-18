package com.stardeux.upprime.media.latest.repository.database

class LatestMediaLocalDataSource (private val latestMediaDao: LatestMediaDao) {

    suspend fun getLatestMedia(): List<LatestMediaEntity> {
        return latestMediaDao.getLatestMedia()
    }

    suspend fun insert(latestMedia: List<LatestMediaEntity>): List<Long> {
        return latestMediaDao.insertAll(latestMedia)
    }
}