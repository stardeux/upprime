package com.stardeux.upprime.latest.ui.mapper

import android.content.Context
import com.stardeux.upprime.core.mapper.mapToString
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.MediaItem

fun mapToMediaUi(context: Context, mediaItem: MediaItem): MediaUi {
    return MediaUi(
        title = mediaItem.title,
        amazonId = mediaItem.amazonId,
        imdbId = mediaItem.imdbId,
        type = mapToString(context, mediaItem.type)
    )
}