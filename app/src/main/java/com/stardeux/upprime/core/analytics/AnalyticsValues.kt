package com.stardeux.upprime.core.analytics

object AnalyticsValues {

    object Screen {
        const val NEW_RELEASE = "new_release"
        const val EXPIRED_RELEASE = "expired"
        const val SEARCH = "search"
        const val FICHE = "fiche"
        const val COUNTRY = "country"
    }

    object Event {
        const val SEARCH_EVENT = "search_event"
        const val SEARCH_RESULTS_CLICKED = "search_results_clicked"

        const val FICHE_VIDEO_CLICKED = "fiche_video_clicked"
        const val FICHE_SHARE = "fiche_share"

        const val COUNTRY_SELECTED = "country_selected"
    }

    object Params {
        const val SEARCH_EVENT_QUERY = "query"
        const val SEARCH_EVENT_YEAR_START = "year_start"
        const val SEARCH_EVENT_YEAR_END = "year_end"
        const val SEARCH_EVENT_MEDIA_TYPE = "media_type"

        const val MEDIA_TITLE = "media_title"
        const val MEDIA_AMAZON_ID = "amazon_id"
        const val MEDIA_IMDB_ID = "imdb_id"

        const val FICHE_VIDEO_KEY = "fiche_video_key"

        const val COUNTRY_ID = "country_id"
    }

    object ParamsValue {
        const val FILTER_MEDIA_TYPE_ALL = "all"
        const val FILTER_MEDIA_TYPE_MOVIE = "movie"
        const val FILTER_MEDIA_TYPE_SERIES = "series"
    }

}