package com.stardeux.upprime.tmdb.video.repository.model

import com.stardeux.upprime.tmdb.video.repository.api.VideoType

data class Video(
    val id: String?,
    val iso_639: String?,
    val key: String?,
    val name: String?,
    val site: String?,
    val size: Int?,
    val type: VideoType?
)