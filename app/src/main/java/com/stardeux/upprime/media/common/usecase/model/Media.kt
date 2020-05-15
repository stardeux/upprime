package com.stardeux.upprime.media.common.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDate

data class Media(
    val title : String?,
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val type: MediaType
)