package com.stardeux.upprime.search.usecase.model

import android.net.Uri
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import org.threeten.bp.LocalDate

data class AmazonSearchResult(
    val title: String?,
    val amazonWebUrl: Uri,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val year: Int?,
    val country: String?,
    val type: MediaType
)