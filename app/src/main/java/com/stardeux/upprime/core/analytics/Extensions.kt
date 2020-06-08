package com.stardeux.upprime.core.analytics

import com.stardeux.upprime.search.ui.model.MediaTypeFilter

fun MediaTypeFilter.getTrackingValue(): String {
    return when(this){
        MediaTypeFilter.ALL -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_ALL
        MediaTypeFilter.MOVIE -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_MOVIE
        MediaTypeFilter.SERIES -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_SERIES
    }
}