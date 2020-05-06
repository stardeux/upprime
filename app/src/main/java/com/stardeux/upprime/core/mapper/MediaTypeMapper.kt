package com.stardeux.upprime.core.mapper

import com.stardeux.upprime.core.model.MediaType

fun mapToMediaType(mediaTypeString: String): MediaType {
    return when (mediaTypeString) {
        "Movie" -> MediaType.MOVIE
        "TV" -> MediaType.SERIES
        else -> throw IllegalStateException("unkown media type $mediaTypeString")
    }
}