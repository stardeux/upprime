package com.stardeux.upprime.latest.usecase.mapper

import com.stardeux.upprime.core.mapper.mapToDate
import com.stardeux.upprime.core.mapper.mapToMediaType
import com.stardeux.upprime.latest.repository.model.MediaItemResponse
import com.stardeux.upprime.latest.usecase.model.MediaItem

fun mapToMediaItem(mediaItemResponse: MediaItemResponse): MediaItem {
    return MediaItem(
        title = mediaItemResponse.title,
        amazonId = requireNotNull(mediaItemResponse.amazonId),
        imdbId = requireNotNull(mediaItemResponse.imdbId),
        dateAdded = mapToDate(requireNotNull(mediaItemResponse.dateAdded)),
        type = mapToMediaType(requireNotNull(mediaItemResponse.type))
    )
}