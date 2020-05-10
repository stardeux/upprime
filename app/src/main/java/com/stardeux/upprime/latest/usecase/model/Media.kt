package com.stardeux.upprime.latest.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDateTime

data class Media(
    val title : String?,
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val dateAdded: LocalDateTime,
    val type: MediaType
)