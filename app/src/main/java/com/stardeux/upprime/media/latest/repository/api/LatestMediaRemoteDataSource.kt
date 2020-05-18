package com.stardeux.upprime.media.latest.repository.api

import com.stardeux.upprime.media.common.repository.model.MediaPageResponse
import com.stardeux.upprime.media.common.usecase.model.AmazonMediaRequest

class LatestMediaRemoteDataSource(private val latestApi: LatestApi) {

    suspend fun getLatest(amazonMediaRequest: AmazonMediaRequest): MediaPageResponse {
        return latestApi.new(amazonMediaRequest.country, amazonMediaRequest.days, amazonMediaRequest.page)
    }
}