package com.stardeux.upprime.search.repository.model

import com.stardeux.upprime.search.repository.api.SearchMediaContainerResponse
import com.stardeux.upprime.search.repository.api.SearchMediaResponse
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer

class AmazonSearchMediaMapper {

    fun mapToAmazonSearchResult(searchMediaResponse: SearchMediaResponse): AmazonSearchResult? {
        return searchMediaResponse.type?.let {
            with(searchMediaResponse) {
                AmazonSearchResult(
                    title = title,
                    amazonId = amazonId,
                    imdbId = imdbId,
                    dateAdded = dateAdded,
                    year = year,
                    country = country,
                    type = it
                )
            }
        }
    }

    fun mapToAmazonSearchResult(searchMediaContainerResponse: SearchMediaContainerResponse): AmazonSearchResultContainer {
        return with(searchMediaContainerResponse) {
            AmazonSearchResultContainer(count = count,
                results = results?.mapNotNull { mapToAmazonSearchResult(it) } ?: emptyList())
        }
    }
}