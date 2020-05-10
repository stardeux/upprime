package com.stardeux.upprime.amazon.common.usecase.model

import com.stardeux.upprime.amazon.common.usecase.model.Media

data class MediaPage(
    val count: Int,
    val media: List<Media>
)