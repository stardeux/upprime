package com.stardeux.upprime.amazon.expired.usecase

import com.stardeux.upprime.amazon.common.model.request.AmazonMediaRequest
import com.stardeux.upprime.amazon.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.amazon.common.model.domain.mapToMediaPage
import com.stardeux.upprime.amazon.common.model.domain.MediaPage

class GetExpiredMediaUseCase(private val expiredMediaRepository: ExpiredMediaRepository) {

    private var currentPage = 0

    suspend fun getExpired(): MediaPage {
        currentPage++
        //TODO inject country
        val request =
            AmazonMediaRequest(
                "gb",
                30,
                currentPage
            )
        return mapToMediaPage(
            expiredMediaRepository.getExpired(request)
        )
    }
}