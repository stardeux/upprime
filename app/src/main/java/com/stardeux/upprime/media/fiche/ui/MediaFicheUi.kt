package com.stardeux.upprime.media.fiche.ui

import android.os.Parcelable
import com.stardeux.upprime.core.model.AmazonId
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaFicheUi(
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
    val backdropPath: String?
) : Parcelable