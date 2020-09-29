package com.stardeux.upprime.media.fiche.ui.model

import android.net.Uri
import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId

data class MediaFicheUi(
    val amazonId: AmazonId,
    val imdbId: ImdbId,
    val tmdbId: TmdbId,
    val title: String?,
    val type: MediaType,
    val runtime: String?,
    val genres: List<String>,
    val mediaReleaseYear: String?,
    val mainNationality: String?,
    val tmdbRating: String?,
    val posterUrl: String?,
    val amazonReleaseDate: String,
    val synopsis: String?,
    val backdropUrl: String?,
    val amazonPlayUri: Uri
)