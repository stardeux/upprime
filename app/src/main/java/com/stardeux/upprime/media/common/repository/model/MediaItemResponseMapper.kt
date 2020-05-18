package com.stardeux.upprime.media.common.repository.model

import com.stardeux.upprime.core.mapper.mapAmazonDateStringToLocaleDate
import com.stardeux.upprime.core.mapper.mapToMediaType
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaEntity
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity

fun mapToMediaItem(mediaResponse: MediaResponse): ShortMedia {
    return ShortMedia(
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
        shortMedia = requireNotNull(mediaPageResponse.results).map(::mapToMediaItem)

    )
}

fun mapToMediaItem(latestMediaEntity: LatestMediaEntity): ShortMedia {
    return with(latestMediaEntity) {
        ShortMedia(
            title = title, amazonId = amazonId, imdbId = imdbId, dateAdded = dateAdded, type = type
        )
    }
}

fun mapLatestToMediaPage(latestMediaEntities: List<LatestMediaEntity>): MediaPage {
    return MediaPage(
        count = Integer.MAX_VALUE, shortMedia = latestMediaEntities.map(::mapToMediaItem)
    )
}

fun mapShortMediaToLatestMediaEntity(shortMedia: ShortMedia): LatestMediaEntity {
    return with(shortMedia) {
        LatestMediaEntity(
            id = 0,
            amazonId = amazonId,
            title = title,
            imdbId = imdbId,
            dateAdded = dateAdded,
            type = type
        )
    }
}



fun mapToMediaItem(expiredMediaEntity: ExpiredMediaEntity): ShortMedia {
    return with(expiredMediaEntity) {
        ShortMedia(
            title = title, amazonId = amazonId, imdbId = imdbId, dateAdded = dateAdded, type = type
        )
    }
}

fun mapExpiredToMediaPage(expiredMediaEntities: List<ExpiredMediaEntity>): MediaPage {
    return MediaPage(
        count = Integer.MAX_VALUE, shortMedia = expiredMediaEntities.map(::mapToMediaItem)
    )
}

fun mapShortMediaToExpiredMediaEntity(shortMedia: ShortMedia): ExpiredMediaEntity {
    return with(shortMedia) {
        ExpiredMediaEntity(
            id = 0,
            amazonId = amazonId,
            title = title,
            imdbId = imdbId,
            dateAdded = dateAdded,
            type = type
        )
    }
}