package com.stardeux.upprime.amazon.common.view

import androidx.annotation.StringRes
import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId

data class MediaUi(
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val tmdbId: TmdbId?,
    val title: String?,
    val type: MediaType,
    val runtime: String?,
    val mainGenre: String?,
    val releaseDate: String?,
    val mainNationality: String?,
    val rating: Float?,
    val posterUrl: String?,
    val amazonReleaseDate: String
) {
    @StringRes
    val mediaTypeStringRes = mapToStringId(type)
}