package com.stardeux.upprime.core.analytics

object AnalyticsValues {

    object Screen {
        const val NEW_RELEASE = "new_release"
        const val EXPIRED_RELEASE = "expired"
        const val SEARCH = "search"
    }

    object Event {
        const val SEARCH_EVENT = "search_event"
    }

    object Params {
        const val SEARCH_EVENT_QUERY = "query"
        const val SEARCH_EVENT_YEAR_START = "year_start"
        const val SEARCH_EVENT_YEAR_END = "year_end"
        const val SEARCH_EVENT_MEDIA_TYPE = "media_type"
    }

    object ParamsValue {
        const val FILTER_MEDIA_TYPE_ALL = "all"
        const val FILTER_MEDIA_TYPE_MOVIE = "movie"
        const val FILTER_MEDIA_TYPE_SERIES = "series"
    }

}