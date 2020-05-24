package com.stardeux.upprime.search.ui.model

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

data class AmazonSearchResultUi(
    val title: String,
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val type: MediaType,
    val year: Int?,
    val onItemClicked: (AmazonSearchResultUi) -> Unit
)