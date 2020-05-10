package com.stardeux.upprime.amazon.expired.usecase

import com.stardeux.upprime.amazon.common.model.request.AmazonMediaRequest
import com.stardeux.upprime.amazon.expired.repository.ExpiredRepository
import com.stardeux.upprime.amazon.common.model.domain.mapToMediaPage
import com.stardeux.upprime.amazon.common.model.domain.MediaPage

class GetExpiredMediaUseCase(private val expiredRepository: ExpiredRepository) {

    private var currentPage = 0

    suspend fun getLatest(): MediaPage {
        currentPage++
        //TODO inject country
        val request =
            AmazonMediaRequest(
                "gb",
                30,
                currentPage
            )
        return mapToMediaPage(
            expiredRepository.getExpired(request)
        )
    }
}