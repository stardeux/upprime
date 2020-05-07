package com.stardeux.upprime.latest.usecase.mapper

import com.stardeux.upprime.core.mapper.mapToDate
import com.stardeux.upprime.core.mapper.mapToMediaType
import com.stardeux.upprime.latest.repository.model.MediaResponse
import com.stardeux.upprime.latest.repository.model.MediaPageResponse
import com.stardeux.upprime.latest.usecase.model.Media
import com.stardeux.upprime.latest.usecase.model.MediaPage

fun mapToMediaItem(mediaResponse: MediaResponse): Media {
    return Media(
        title = mediaResponse.title,
        amazonId = requireNotNull(mediaResponse.amazonId),
        imdbId = requireNotNull(mediaResponse.imdbId),
        dateAdded = mapToDate(requireNotNull(mediaResponse.dateAdded)),
        type = mapToMediaType(requireNotNull(mediaResponse.type))
    )
}

fun mapToMediaPage(mediaPageResponse: MediaPageResponse): MediaPage {
    return MediaPage(
        count = requireNotNull(mediaPageResponse.count),
        media = requireNotNull(mediaPageResponse.results).map(::mapToMediaItem)

    )
}