package com.stardeux.upprime.latest.usecase.model

import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDateTime

data class Media(
    val title : String?,
    val amazonId: String,
    val imdbId: String,
    val dateAdded: LocalDateTime,
    val type: MediaType
)