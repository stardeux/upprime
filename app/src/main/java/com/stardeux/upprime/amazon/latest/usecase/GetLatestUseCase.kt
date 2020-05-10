package com.stardeux.upprime.amazon.latest.usecase

import com.stardeux.upprime.amazon.latest.repository.LatestRepository
import com.stardeux.upprime.amazon.common.model.request.AmazonMediaRequest
import com.stardeux.upprime.amazon.latest.usecase.mapper.mapToMediaPage
import com.stardeux.upprime.amazon.latest.usecase.model.MediaPage

class GetLatestUseCase(private val latestRepository: LatestRepository) {

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
        return mapToMediaPage(latestRepository.getNew(request))
    }
}