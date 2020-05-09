package com.stardeux.upprime.latest.ui.model

import androidx.annotation.StringRes

data class MediaUi(
    val title: String?,
    val amazonId: String,
    val imdbId: String,
    @StringRes val typeText: Int,
    val posterUrl: String?
)