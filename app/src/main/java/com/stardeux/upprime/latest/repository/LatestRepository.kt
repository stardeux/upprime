package com.stardeux.upprime.latest.repository

import com.stardeux.upprime.latest.repository.api.LatestApi
import com.stardeux.upprime.latest.repository.model.MediaPageResponse
import com.stardeux.upprime.latest.repository.model.ReleaseRequest

class LatestRepository(private val latestApi: LatestApi) {

    suspend fun getNew(releaseRequest: ReleaseRequest): MediaPageResponse {
        return latestApi.new(releaseRequest.country, releaseRequest.days, releaseRequest.page)
    }
}