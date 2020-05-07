package com.stardeux.upprime.core.mapper

import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.MediaType

fun mapToMediaType(mediaTypeString: String): MediaType {
    return when (mediaTypeString) {
        "Movie" -> MediaType.MOVIE
        "TV" -> MediaType.SERIES
        else -> throw IllegalStateException("unkown media type $mediaTypeString")
    }
}

fun mapToStringId(mediaType: MediaType): Int {
    return when (mediaType) {
        MediaType.MOVIE -> R.string.movie
        MediaType.SERIES -> R.string.series
    }
}