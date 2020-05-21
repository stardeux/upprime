package com.stardeux.upprime.media.fiche.ui.video

import com.stardeux.upprime.tmdb.video.repository.api.VideoType

data class MediaVideoUi(
    val key: String?,
    val site: String?,
    val type: VideoType?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    val onMediaVideoClicked: (MediaVideoUi) -> Unit
)