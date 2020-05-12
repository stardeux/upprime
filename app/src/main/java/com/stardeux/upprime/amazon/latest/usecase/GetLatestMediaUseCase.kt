package com.stardeux.upprime.amazon.latest.usecase

import com.stardeux.upprime.amazon.latest.repository.LatestMediaRepository
import com.stardeux.upprime.amazon.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.amazon.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.amazon.common.usecase.model.MediaPage
import com.stardeux.upprime.country.usecase.model.AvailableCountry

class GetLatestMediaUseCase(
    private val latestMediaRepository: LatestMediaRepository,
    private val availableCountry: AvailableCountry
) {

    suspend fun getLatest(page: Int): MediaPage {
        //TODO inject country
        val request = AmazonMediaRequest("gb", page)
        return mapToMediaPage(latestMediaRepository.getNew(request))
    }
}