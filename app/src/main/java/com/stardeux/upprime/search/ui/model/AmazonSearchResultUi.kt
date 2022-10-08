package com.stardeux.upprime.search.ui.model

import android.net.Uri
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDate

data class AmazonSearchResultUi(
    val title: String,
    val amazonWebUrl: Uri,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val type: MediaType,
    val year: Int?,
    val onItemClicked: (AmazonSearchResultUi) -> Unit
)