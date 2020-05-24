package com.stardeux.upprime.search.repository.model

import com.stardeux.upprime.media.common.repository.model.mapToMediaType
import com.stardeux.upprime.search.repository.api.SearchMediaContainerResponse
import com.stardeux.upprime.search.repository.api.SearchMediaResponse
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer

class AmazonSearchMediaMapper {

    fun mapToAmazonSearchResult(searchMediaResponse: SearchMediaResponse): AmazonSearchResult? {
        return if (searchMediaResponse.type != null && searchMediaResponse.dateAdded != null) {
            with(searchMediaResponse) {
                AmazonSearchResult(
                    title = title,
                    amazonId = amazonId,
                    imdbId = imdbId,
                    dateAdded = dateAdded!!.toLocalDate(),
                    year = year,
                    country = country,
                    type = mapToMediaType(type!!)
                )
            }
        } else {
            null
        }
    }

    fun mapToAmazonSearchResult(searchMediaContainerResponse: SearchMediaContainerResponse): AmazonSearchResultContainer {
        return with(searchMediaContainerResponse) {
            AmazonSearchResultContainer(count = count,
                results = results?.mapNotNull { mapToAmazonSearchResult(it) } ?: emptyList())
        }
    }
}