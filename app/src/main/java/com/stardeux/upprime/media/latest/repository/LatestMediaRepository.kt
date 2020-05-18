package com.stardeux.upprime.media.latest.repository

import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.repository.model.mapToMediaPage
import com.stardeux.upprime.media.latest.repository.api.LatestMediaRemoteDataSource
import com.stardeux.upprime.media.latest.repository.database.LatestMediaLocalDataSource
import com.stardeux.upprime.media.latest.repository.preferences.LatestMediaPreferences

class LatestMediaRepository(
    private val latestMediaRemoteDataSource: LatestMediaRemoteDataSource,
    private val latestMediaLocalDataSource: LatestMediaLocalDataSource,
    private val latestMediaPreferences: LatestMediaPreferences
) {

    suspend fun getLatest(amazonMediaRequest: AmazonMediaRequest): MediaPage {
        val fromId = ((amazonMediaRequest.page - 1) * PAGE_SIZE).toLong()
        val limit = amazonMediaRequest.page * PAGE_SIZE
        val localResult = latestMediaLocalDataSource.getLatestMedia(fromId, limit)
        return if (localResult.isNotEmpty()) {
            mapToMediaPage(localResult)
        } else {
            mapToMediaPage(latestMediaRemoteDataSource.getLatest(amazonMediaRequest)).also {
                //latestMediaLocalDataSource.insert(it)
                latestMediaPreferences.setLatestMediaHasBeenRequested()
            }
        }
    }

    suspend fun clearCache() {
        latestMediaLocalDataSource.clearTable()
    }

    companion object {
        private const val PAGE_SIZE = 100
    }

}