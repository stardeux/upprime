package com.stardeux.upprime.search.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import org.threeten.bp.LocalDateTime

data class AmazonSearchResult(
    val title: String?,
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val dateAdded: LocalDateTime?,
    val year: Int?,
    val country: String?,
    val type: MediaTypeResponse
)