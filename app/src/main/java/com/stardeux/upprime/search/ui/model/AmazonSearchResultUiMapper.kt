package com.stardeux.upprime.search.ui.model

import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.search.usecase.model.AmazonSearchResult

class AmazonSearchResultUiMapper {

    fun mapToShortMedia(amazonSearchResultUi: AmazonSearchResultUi): ShortMedia {
        return with(amazonSearchResultUi) {
            ShortMedia(
                title = title,
                amazonId = "TODO",
                imdbId = imdbId,
                dateAdded = dateAdded,
                type = type,
                amazonWebUrl = amazonWebUrl
            )
        }
    }

    fun mapToAmazonSearchResultUi(
        amazonSearchResult: AmazonSearchResult, onItemClicked: (AmazonSearchResultUi) -> Unit
    ): AmazonSearchResultUi? {
        return with(amazonSearchResult) {
            title?.let { title ->
                AmazonSearchResultUi(
                    amazonId = amazonId,
                    title = title,
                    amazonWebUrl = amazonWebUrl,
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