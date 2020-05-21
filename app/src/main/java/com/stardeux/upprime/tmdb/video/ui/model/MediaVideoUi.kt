package com.stardeux.upprime.tmdb.video.ui.model

import com.stardeux.upprime.tmdb.video.repository.api.model.VideoType

data class MediaVideoUi(
    val key: String?,
    val site: String?,
    val type: VideoType?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    val onMediaVideoClicked: (MediaVideoUi) -> Unit
)