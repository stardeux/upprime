package com.stardeux.upprime.media.expired.usecase

import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.common.repository.model.mapToMediaPage
import com.stardeux.upprime.media.common.usecase.model.MediaPage
import com.stardeux.upprime.media.common.usecase.model.mapAvailableCountryToApiValue

class GetExpiredMediaUseCase(
    private val expiredMediaRepository: ExpiredMediaRepository,
    private val availableCountry: AvailableCountry
) {

    suspend fun getExpired(page: Int): MediaPage {
        val request = AmazonMediaRequest(mapAvailableCountryToApiValue(availableCountry), page)
        return mapToMediaPage(
            expiredMediaRepository.getExpired(request)
        )
    }
}