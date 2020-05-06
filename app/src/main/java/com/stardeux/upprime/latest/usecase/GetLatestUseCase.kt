package com.stardeux.upprime.latest.usecase

import com.stardeux.upprime.latest.repository.LatestRepository
import com.stardeux.upprime.latest.usecase.mapper.mapToMediaPage
import com.stardeux.upprime.latest.usecase.model.MediaPage

class GetLatestUseCase(private val latestRepository: LatestRepository) {

    suspend fun getLatest(): MediaPage {
        return mapToMediaPage(latestRepository.getNew())
    }
}