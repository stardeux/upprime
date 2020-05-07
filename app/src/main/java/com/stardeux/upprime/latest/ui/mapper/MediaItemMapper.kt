package com.stardeux.upprime.latest.ui.mapper

import android.content.Context
import com.stardeux.upprime.core.mapper.mapToString
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media

fun mapToMediaUi(context: Context, media: Media): MediaUi {
    return MediaUi(
        title = media.title,
        amazonId = media.amazonId,
        imdbId = media.imdbId,
        type = mapToString(context, media.type)
    )
}