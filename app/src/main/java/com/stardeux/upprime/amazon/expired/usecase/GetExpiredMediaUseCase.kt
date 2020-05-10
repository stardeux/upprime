package com.stardeux.upprime.amazon.expired.usecase

import com.stardeux.upprime.amazon.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.amazon.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.amazon.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.amazon.common.usecase.model.MediaPage

class GetExpiredMediaUseCase(private val expiredMediaRepository: ExpiredMediaRepository) {

    suspend fun getExpired(page: Int): MediaPage {
        val request = AmazonMediaRequest("gb", page)
        return mapToMediaPage(expiredMediaRepository.getExpired(request))
    }
}