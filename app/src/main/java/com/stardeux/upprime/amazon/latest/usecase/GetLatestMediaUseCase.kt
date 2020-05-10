package com.stardeux.upprime.amazon.latest.usecase

import com.stardeux.upprime.amazon.latest.repository.LatestMediaRepository
import com.stardeux.upprime.amazon.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.amazon.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.amazon.common.usecase.model.MediaPage

class GetLatestMediaUseCase(private val latestMediaRepository: LatestMediaRepository) {

    suspend fun getLatest(page: Int): MediaPage {
        //TODO inject country
        val request = AmazonMediaRequest("gb", 30, page)
        return mapToMediaPage(latestMediaRepository.getNew(request))
    }
}