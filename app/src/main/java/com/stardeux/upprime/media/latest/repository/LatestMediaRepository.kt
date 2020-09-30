package com.stardeux.upprime.media.latest.repository

import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.repository.api.MediaPageResponse
import com.stardeux.upprime.media.common.repository.model.mapper.ShortMediaMapper
import com.stardeux.upprime.media.latest.repository.api.LatestMediaRemoteDataSource
import com.stardeux.upprime.media.latest.repository.database.LatestMediaLocalDataSource
import com.stardeux.upprime.media.latest.repository.preferences.LatestMediaPreferences

class LatestMediaRepository(
    private val shortMediaMapper: ShortMediaMapper,
    private val latestMediaRemoteDataSource: LatestMediaRemoteDataSource,
    private val latestMediaLocalDataSource: LatestMediaLocalDataSource,
    private val latestMediaPreferences: LatestMediaPreferences
) {

    suspend fun getLatest(amazonMediaRequest: AmazonMediaRequest): MediaPage {
        val fromId = ((amazonMediaRequest.page - 1) * MediaPageResponse.PAGE_SIZE).toLong()
        val localResult = latestMediaLocalDataSource.getLatestMedia(fromId, MediaPageResponse.PAGE_SIZE)
        return if (localResult.isNotEmpty()) {
            shortMediaMapper.mapLatestToMediaPage(localResult)
        } else {
            shortMediaMapper.mapToMediaPage(latestMediaRemoteDataSource.getLatest(amazonMediaRequest)).also {
                latestMediaLocalDataSource.insert(it.shortMedia.map(shortMediaMapper::mapShortMediaToLatestMediaEntity))
                latestMediaPreferences.setLatestMediaHasBeenRequested()
            }
        }
    }

    suspend fun clearCache() {
        latestMediaLocalDataSource.clearTable()
    }
}