package com.stardeux.upprime.tmdb.common.request

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import org.threeten.bp.LocalDate

data class ImdbMediaRequest(
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val amazonReleaseDate: LocalDate,
    val name: String?
)