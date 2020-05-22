package com.stardeux.upprime.core.mapper

import androidx.annotation.StringRes
import com.stardeux.upprime.R
import com.stardeux.upprime.core.model.MediaType

@StringRes fun mapMediaTypeToStringId(mediaType: MediaType): Int {
    return when (mediaType) {
        MediaType.MOVIE -> R.string.movie
        MediaType.SERIES -> R.string.series
    }
}