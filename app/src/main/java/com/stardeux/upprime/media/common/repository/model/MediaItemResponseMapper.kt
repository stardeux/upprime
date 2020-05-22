package com.stardeux.upprime.media.common.repository.model

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.api.MediaPageResponse
import com.stardeux.upprime.media.common.repository.api.MediaResponse
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import com.stardeux.upprime.media.expired.repository.database.ExpiredMediaEntity
import com.stardeux.upprime.media.latest.repository.database.LatestMediaEntity

fun mapToMediaItem(mediaResponse: MediaResponse): ShortMedia? {
    return mediaResponse.type?.let {
        ShortMedia(
            title = mediaResponse.title,
            amazonId = requireNotNull(mediaResponse.amazonId),
            imdbId = requireNotNull(mediaResponse.imdbId),
            dateAdded = requireNotNull(mediaResponse.dateAdded).toLocalDate(),
            type = mapToMediaType(it)
        )
    }
}

fun mapToMediaType(mediaTypeResponse: MediaTypeResponse): MediaType {
    return when (mediaTypeResponse) {
        MediaTypeResponse.MOVIE -> MediaType.MOVIE
        MediaTypeResponse.SERIES -> MediaType.SERIES
    }
}

fun mapToMediaPage(mediaPageResponse: MediaPageResponse): MediaPage {
    return MediaPage(
        count = requireNotNull(mediaPageResponse.count),
        shortMedia = requireNotNull(mediaPageResponse.results).mapNotNull(::mapToMediaItem)

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