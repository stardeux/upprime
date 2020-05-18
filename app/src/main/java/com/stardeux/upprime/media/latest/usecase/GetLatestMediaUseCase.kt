package com.stardeux.upprime.media.latest.usecase

import com.stardeux.upprime.media.latest.repository.LatestMediaRepository
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.common.repository.model.MediaPage
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.usecase.model.mapAvailableCountryToApiValue
import com.stardeux.upprime.media.latest.repository.preferences.LatestMediaPreferences
import org.threeten.bp.OffsetDateTime

class GetLatestMediaUseCase(
    private val latestMediaPreferences: LatestMediaPreferences,
    private val latestMediaRepository: LatestMediaRepository,
    private val availableCountry: AvailableCountry
) {

    suspend fun getLatest(page: Int): MediaPage {
        val latestRequestDate = latestMediaPreferences.getPreviousMediaRequestDate()
        if (latestRequestDate?.dayOfYear != OffsetDateTime.now().dayOfYear){
            latestMediaRepository.clearCache()
        }

        val request = AmazonMediaRequest(mapAvailableCountryToApiValue(availableCountry), page)
        return latestMediaRepository.getLatest(request)
    }
}