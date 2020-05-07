package com.stardeux.upprime.latest.ui.mapper

import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media

fun mapToMediaUi(media: Media): MediaUi {
    return MediaUi(
        title = media.title,
        amazonId = media.amazonId,
        imdbId = media.imdbId,
        type = mapToStringId(media.type),
        posterUrl = null
    )
}