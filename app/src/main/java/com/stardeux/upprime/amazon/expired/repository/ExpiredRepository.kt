package com.stardeux.upprime.amazon.expired.repository

import com.stardeux.upprime.amazon.common.model.request.AmazonMediaRequest
import com.stardeux.upprime.amazon.common.model.response.MediaPageResponse
import com.stardeux.upprime.amazon.expired.repository.api.ExpiredApi

class ExpiredRepository(private val expiredApi: ExpiredApi) {

    suspend fun expired(amazonMediaRequest: AmazonMediaRequest): MediaPageResponse {
        return expiredApi.expired(
            amazonMediaRequest.country,
            amazonMediaRequest.days,
            amazonMediaRequest.page
        )
    }
}