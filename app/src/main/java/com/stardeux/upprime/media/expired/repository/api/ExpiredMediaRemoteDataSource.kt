package com.stardeux.upprime.media.expired.repository.api

import com.stardeux.upprime.media.common.repository.model.MediaPageResponse
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest

class ExpiredMediaRemoteDataSource(private val expiredApi: ExpiredApi) {

    suspend fun getExpired(amazonMediaRequest: AmazonMediaRequest): MediaPageResponse {
        return expiredApi.expired(
            amazonMediaRequest.country, amazonMediaRequest.days, amazonMediaRequest.page
        )
    }
}