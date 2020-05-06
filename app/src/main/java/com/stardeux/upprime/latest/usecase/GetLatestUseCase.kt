package com.stardeux.upprime.latest.usecase

import com.stardeux.upprime.latest.repository.LatestRepository
import com.stardeux.upprime.latest.repository.model.MediaPageResponse

class GetLatestUseCase(private val latestRepository: LatestRepository) {

    suspend fun getLatest(): MediaPageResponse {
        return latestRepository.getNew()
    }
}