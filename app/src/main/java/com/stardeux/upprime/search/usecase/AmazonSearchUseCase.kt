package com.stardeux.upprime.search.usecase

import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.usecase.model.apiValue
import com.stardeux.upprime.search.repository.AmazonSearchRepository
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer

class AmazonSearchUseCase(
    private val amazonSearchRepository: AmazonSearchRepository,
    private val availableCountry: AvailableCountry
) {

    fun search(amazonSearchRequest: AmazonSearchRequest): AmazonSearchResultContainer {
        return amazonSearchRepository.search(amazonSearchRequest, availableCountry.apiValue)
    }

}