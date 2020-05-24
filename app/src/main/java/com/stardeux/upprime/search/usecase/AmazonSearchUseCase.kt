package com.stardeux.upprime.search.usecase

import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import com.stardeux.upprime.media.common.usecase.model.apiValue
import com.stardeux.upprime.search.repository.AmazonSearchRepository
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer
import org.threeten.bp.LocalDateTime

class AmazonSearchUseCase(
    private val amazonSearchRepository: AmazonSearchRepository,
    private val availableCountry: AvailableCountry
) {

    suspend fun search(amazonSearchRequest: AmazonSearchRequest): AmazonSearchResultContainer {
        val amazonSearchResult = AmazonSearchResult(
            "titanic",
            "",
            "",
            LocalDateTime.now(),
            2020,
            "us",
            MediaTypeResponse.MOVIE
        )
        return AmazonSearchResultContainer(0, listOf(amazonSearchResult))
        //return amazonSearchRepository.search(amazonSearchRequest, availableCountry.apiValue)
    }

}