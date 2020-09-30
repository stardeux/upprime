package com.stardeux.upprime.media.common.repository.model.mapper

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse

fun mapToMediaType(mediaTypeResponse: MediaTypeResponse): MediaType {
    return when (mediaTypeResponse) {
        MediaTypeResponse.MOVIE -> MediaType.MOVIE
        MediaTypeResponse.SERIES -> MediaType.SERIES
    }
}