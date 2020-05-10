package com.stardeux.upprime.tmdb.common.request

import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.TmdbId
import org.threeten.bp.LocalDate

data class TmdbMovieRequest(
    val imdbId: ImdbId,
    val amazonId: AmazonId,
    val tmdbId: TmdbId?,
    val amazonReleaseDate: LocalDate,
    val name: String?
)