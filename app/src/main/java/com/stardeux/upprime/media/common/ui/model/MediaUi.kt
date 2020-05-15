package com.stardeux.upprime.media.common.ui.model

import androidx.annotation.StringRes
import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.media.common.usecase.model.Media

data class MediaUi(
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val tmdbId: TmdbId?,
    val title: String?,
    val type: MediaType,
    val runtime: String?,
    val mainGenre: String?,
    val mediaReleaseYear: String?,
    val mainNationality: String?,
    val rating: String?,
    val posterUrl: String?,
    val amazonReleaseDate: String,
    val synopsis: String?,
    val onCardClicked: (MediaUi) -> Unit
) {
    @StringRes
    val mediaTypeStringRes = mapToStringId(type)
}