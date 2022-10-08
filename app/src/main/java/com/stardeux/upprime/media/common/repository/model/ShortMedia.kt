package com.stardeux.upprime.media.common.repository.model

import android.net.Uri
import android.os.Parcelable
import com.stardeux.upprime.core.model.ImdbId
import com.stardeux.upprime.core.model.MediaType
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDate

@Parcelize
data class ShortMedia(
    val title : String?,
    val imdbId: ImdbId,
    val dateAdded: LocalDate,
    val type: MediaType,
    val amazonWebUrl: Uri
) : Parcelable