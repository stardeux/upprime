package com.stardeux.upprime.media.expired.repository

import com.stardeux.upprime.media.common.repository.model.*
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.expired.repository.api.ExpiredMediaRemoteDataSource
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaLocalDataSource

class ExpiredMediaRepository(
    private val expiredMediaLocalDataSource: ExpiredMediaLocalDataSource,
    private val expiredMediaRemoteDataSource: ExpiredMediaRemoteDataSource
) {

    suspend fun getExpired(amazonMediaRequest: AmazonMediaRequest): MediaPage {
        val fromId = ((amazonMediaRequest.page - 1) * MediaPageResponse.PAGE_SIZE).toLong()
        val localResult = expiredMediaLocalDataSource.getExpiredMedia(fromId,
            MediaPageResponse.PAGE_SIZE
        )
        return if (localResult.isNotEmpty()) {
            mapExpiredToMediaPage(localResult)
        } else {
            mapToMediaPage(expiredMediaRemoteDataSource.getExpired(amazonMediaRequest)).also {
                expiredMediaLocalDataSource.insert(it.shortMedia.map(::mapShortMediaToExpiredMediaEntity))
            }
        }
    }
}