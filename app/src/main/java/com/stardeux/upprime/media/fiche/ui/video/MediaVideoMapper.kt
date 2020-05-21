package com.stardeux.upprime.media.fiche.ui.video

import com.stardeux.upprime.tmdb.video.usecase.MediaVideo

class MediaVideoMapper {

    fun mapToMediaVideoUi(video: MediaVideo, onMediaVideoClicked: (MediaVideoUi) -> Unit): MediaVideoUi {
        return with(video) {
            MediaVideoUi(
                thumbnailUrl = thumbnailUrl,
                key = key,
                site = site,
                type = type,
                onMediaVideoClicked = onMediaVideoClicked
            )
        }
    }

}