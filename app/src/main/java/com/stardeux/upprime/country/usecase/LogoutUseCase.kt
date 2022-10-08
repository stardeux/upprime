package com.stardeux.upprime.country.usecase

import com.stardeux.upprime.country.di.getUserScope
import com.stardeux.upprime.media.expired.repository.ExpiredMediaRepository
import com.stardeux.upprime.media.latest.repository.LatestMediaRepository

class LogoutUseCase(
    private val latestMediaRepository: LatestMediaRepository,
    private val expiredMediaRepository: ExpiredMediaRepository
) {

    suspend fun logout() {
        latestMediaRepository.clearCache()
        expiredMediaRepository.clearCache()

        getUserScope().close()
    }

}