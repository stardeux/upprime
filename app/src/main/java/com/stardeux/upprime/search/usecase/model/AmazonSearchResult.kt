package com.stardeux.upprime.search.usecase.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.media.common.repository.api.MediaTypeResponse
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

data class AmazonSearchResult(
    val amazonId: AmazonId,
    val title: String?,
    val amazonWebUrl: String,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val year: Int?,
    val country: String?,
    val type: MediaType
)