package com.stardeux.upprime.amazon.common.model.domain

import com.stardeux.upprime.amazon.common.model.domain.Media

data class MediaPage(
    val count: Int,
    val media: List<Media>
)