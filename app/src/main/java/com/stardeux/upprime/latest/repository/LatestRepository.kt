package com.stardeux.upprime.latest.repository

import com.stardeux.upprime.latest.repository.api.LatestApi
import com.stardeux.upprime.latest.repository.model.MediaPageResponse

class LatestRepository(private val latestApi: LatestApi) {

    suspend fun getNew(): MediaPageResponse {
        return latestApi.new()
    }
}