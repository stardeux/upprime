package com.stardeux.upprime.media.expired.usecase

import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.common.repository.model.mapToMediaPage
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.media.common.usecase.model.mapAvailableCountryToApiValue
import com.stardeux.upprime.media.expired.repository.preferences.ExpiredMediaPreferences
import org.threeten.bp.OffsetDateTime

class GetExpiredMediaUseCase(
    private val expiredMediaRepository: ExpiredMediaRepository,
    private val availableCountry: AvailableCountry,
    private val expiredMediaPreferences: ExpiredMediaPreferences
) {

    suspend fun getExpired(page: Int): MediaPage {
        val expiredRequestDate = expiredMediaPreferences.getPreviousMediaRequestDate()
        if (expiredRequestDate?.dayOfYear != OffsetDateTime.now().dayOfYear){
            expiredMediaRepository.clearCache()
        }
        
        val request = AmazonMediaRequest(mapAvailableCountryToApiValue(availableCountry), page)
        return expiredMediaRepository.getExpired(request)
    }
}