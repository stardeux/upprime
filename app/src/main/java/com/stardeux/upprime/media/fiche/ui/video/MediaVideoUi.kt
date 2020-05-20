package com.stardeux.upprime.media.fiche.ui.video

import com.stardeux.upprime.tmdb.video.repository.api.VideoType

data class MediaVideoUi(
    val thumbnailUrl: String?,
    val key: String?,
    val site: String?,
    val type: VideoType?,
    val onMediaVideoClicked: (MediaVideoUi) -> Unit
)