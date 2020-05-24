package com.stardeux.upprime.search.ui.model

import com.stardeux.upprime.media.common.repository.model.mapToMediaType
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult

class AmazonSearchResultUiMapper {

    fun mapToAmazonSearchResultUi(amazonSearchResult: AmazonSearchResult): AmazonSearchResultUi? {
        return with(amazonSearchResult) {
            title?.let { title ->
                AmazonSearchResultUi(
                    title = title,
                    amazonId = amazonId,
                    imdbId = imdbId,
                    dateAdded = dateAdded,
                    type = mapToMediaType(type),
                    year = year
                )
            }

        }
    }

}