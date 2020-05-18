package com.stardeux.upprime.media.common.repository.model

import com.stardeux.upprime.core.mapper.mapAmazonDateStringToLocaleDate
import com.stardeux.upprime.core.mapper.mapToMediaType
import com.stardeux.upprime.media.common.usecase.model.Media
import com.stardeux.upprime.media.common.usecase.model.MediaPage
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity

fun mapToMediaItem(mediaResponse: MediaResponse): Media {
    return Media(
        title = mediaResponse.title,
        amazonId = requireNotNull(mediaResponse.amazonId),
        imdbId = requireNotNull(mediaResponse.imdbId),
        dateAdded = mapAmazonDateStringToLocaleDate(requireNotNull(mediaResponse.dateAdded)),
        type = mapToMediaType(requireNotNull(mediaResponse.type))
    )
}

fun mapToMediaPage(mediaPageResponse: MediaPageResponse): MediaPage {
    return MediaPage(
        count = requireNotNull(mediaPageResponse.count),
        media = requireNotNull(mediaPageResponse.results).map(::mapToMediaItem)

    )
}

fun mapToMediaItem(latestMediaEntity: LatestMediaEntity): Media {
    return with (latestMediaEntity) {
        Media(
            title = title,
            amazonId = amazonId,
            imdbId = imdbId,
            dateAdded = mapAmazonDateStringToLocaleDate(dateAdded),
            type = type
        )
    }
}

fun mapToMediaPage(latestMediaEntities: List<LatestMediaEntity>): MediaPage {
    return MediaPage(
        count = latestMediaEntities.size, media = latestMediaEntities.map(::mapToMediaItem)
    )
}