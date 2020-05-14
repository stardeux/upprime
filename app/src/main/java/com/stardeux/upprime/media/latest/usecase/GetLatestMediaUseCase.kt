package com.stardeux.upprime.media.latest.usecase

import com.stardeux.upprime.media.latest.repository.LatestMediaRepository
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.media.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.media.common.usecase.model.MediaPage
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.usecase.model.mapAvailableCountryToApiValue

class GetLatestMediaUseCase(
    private val latestMediaRepository: LatestMediaRepository,
    private val availableCountry: AvailableCountry
) {

    suspend fun getLatest(page: Int): MediaPage {
        val request = AmazonMediaRequest(mapAvailableCountryToApiValue(availableCountry), page)
        return mapToMediaPage(latestMediaRepository.getNew(request))
    }
}