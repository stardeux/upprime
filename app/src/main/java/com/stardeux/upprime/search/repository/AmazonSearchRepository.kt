package com.stardeux.upprime.search.repository

import com.stardeux.upprime.search.repository.api.AmazonSearchApi
import com.stardeux.upprime.search.repository.model.AmazonSearchMediaMapper
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer

class AmazonSearchRepository(
    private val amazonSearchApi: AmazonSearchApi, private val amazonSearchMediaMapper: AmazonSearchMediaMapper
) {

    suspend fun search(
        amazonSearchRequest: AmazonSearchRequest, country: String
    ): AmazonSearchResultContainer {
        return amazonSearchMediaMapper.mapToAmazonSearchResult(
            amazonSearchApi.search(
                amazonSearchRequest.title,
                country,
                amazonSearchRequest.yearStart,
                amazonSearchRequest.yearEnd,
                amazonSearchRequest.page
            )
        )
    }

}