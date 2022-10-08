package com.stardeux.upprime.tmdb.common.request

import android.os.Parcelable
import com.stardeux.upprime.core.model.ImdbId
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class ImdbMediaRequest(
    val imdbId: ImdbId,
    val amazonReleaseDate: LocalDate,
    val name: String?,
    val language: String
) : Parcelable