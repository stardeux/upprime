package com.stardeux.upprime.core.mapper

import android.content.Context
import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.MediaType

fun mapToMediaType(mediaTypeString: String): MediaType {
    return when (mediaTypeString) {
        "Movie" -> MediaType.MOVIE
        "TV" -> MediaType.SERIES
        else -> throw IllegalStateException("unkown media type $mediaTypeString")
    }
}

fun mapToString(context: Context, mediaType: MediaType): String {
    return when (mediaType) {
        MediaType.MOVIE -> context.getString(R.string.movie)
        MediaType.SERIES -> context.getString(R.string.series)
    }
}