package com.stardeux.upprime.media.expired.usecase

import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.media.common.usecase.model.MediaPage

class GetExpiredMediaUseCase(private val expiredMediaRepository: ExpiredMediaRepository) {

    suspend fun getExpired(page: Int): MediaPage {
        val request = AmazonMediaRequest("gb", page)
        return mapToMediaPage(expiredMediaRepository.getExpired(request))
    }
}