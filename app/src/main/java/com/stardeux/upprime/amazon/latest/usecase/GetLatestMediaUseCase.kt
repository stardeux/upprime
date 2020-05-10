package com.stardeux.upprime.amazon.latest.usecase

import com.stardeux.upprime.amazon.latest.repository.LatestMediaRepository
import com.stardeux.upprime.amazon.common.usecase.model.AmazonMediaRequest
import com.stardeux.upprime.amazon.common.usecase.model.mapToMediaPage
import com.stardeux.upprime.amazon.common.usecase.model.MediaPage

class GetLatestMediaUseCase(private val latestMediaRepository: LatestMediaRepository) {

    private var currentPage = 0

    suspend fun getLatest(): MediaPage {
        currentPage++
        //TODO inject country
        val request =
            AmazonMediaRequest(
                "gb", 30, currentPage
            )
        return mapToMediaPage(
            latestMediaRepository.getNew(request)
        )
    }
}