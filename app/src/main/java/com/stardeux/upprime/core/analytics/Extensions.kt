package com.stardeux.upprime.core.analytics

import android.os.Bundle
import androidx.core.os.bundleOf
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi

fun MediaTypeFilter.getTrackingValue(): String {
    return when (this) {
        MediaTypeFilter.ALL -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_ALL
        MediaTypeFilter.MOVIE -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_MOVIE
        MediaTypeFilter.SERIES -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_SERIES
    }
}

fun ShortMedia.getTrackingParameters(): Bundle {
    return bundleOf(
        AnalyticsValues.Params.MEDIA_TITLE to title,
        AnalyticsValues.Params.MEDIA_IMDB_ID to imdbId,
        AnalyticsValues.Params.MEDIA_AMAZON_ID to amazonId
    )
}

fun MediaVideoUi.getTrackingParameters(): Bundle {
    return bundleOf(
        AnalyticsValues.Params.FICHE_VIDEO_KEY to key
    )
}