package com.stardeux.upprime.search.ui.model

import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.repository.model.mapToMediaType
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult

class AmazonSearchResultUiMapper {

    fun mapToShortMedia(amazonSearchResultUi: AmazonSearchResultUi): ShortMedia {
        return with(amazonSearchResultUi) {
            ShortMedia(
                title = title,
                amazonId = amazonId,
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type
            )
        }
    }

    fun mapToAmazonSearchResultUi(
        amazonSearchResult: AmazonSearchResult, onItemClicked: (AmazonSearchResultUi) -> Unit
    ): AmazonSearchResultUi? {
        return with(amazonSearchResult) {
            title?.let { title ->
                AmazonSearchResultUi(
                    title = title,
                    amazonId = amazonId,
                    imdbId = imdbId,
                    dateAdded = dateAdded,
                    type = type,
                    year = year,
                    onItemClicked = onItemClicked
                )
            }

        }
    }

}