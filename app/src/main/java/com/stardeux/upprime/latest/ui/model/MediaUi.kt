package com.stardeux.upprime.latest.ui.model

import androidx.annotation.StringRes
import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.core.model.MediaType

data class MediaUi(
    val title: String?,
    val type: MediaType,
    val amazonId: String,
    val imdbId: String,
    val posterUrl: String?
) {
    @StringRes val mediaTypeStringRes = mapToStringId(type)
}