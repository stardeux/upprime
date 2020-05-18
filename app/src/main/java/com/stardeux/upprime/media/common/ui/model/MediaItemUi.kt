package com.stardeux.upprime.media.common.ui.model

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.media.common.usecase.model.ShortMedia

data class MediaItemUi(
    val shortMedia: ShortMedia,
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
    val backdropPath: String?,
    val onCardClicked: (MediaItemUi) -> Unit
)