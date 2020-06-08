package com.stardeux.upprime.core.analytics

object AnalyticsValues {

    object Screen {
        const val NEW_RELEASE = "new_release"
        const val EXPIRED_RELEASE = "expired"
        const val SEARCH = "search"
        const val FICHE = "fiche"
        const val COUNTRY = "country"
        const val SPLASH_SCREEN = "splash_screen"
    }

    object Event {
        const val SEARCH_CLICKED = "search_clicked"
        const val SEARCH_QUERY = "search_query"
        const val SEARCH_RESULTS_CLICKED = "search_results_clicked"

        const val FICHE_VIDEO_CLICKED = "fiche_video_clicked"
        const val FICHE_SHARE = "fiche_share"

        const val COUNTRY_SELECTED = "country_selected"

        const val MEDIA_ITEM_CLICKED = "media_item_clicked"

        const val SHARE_APP_CLICKED = "share_app_clicked"
        const val COUNTRY_CLICKED = "search"
        const val RATE_APP_CLICKED = "rate_app_clicked"
        const val RATE_APP_ANSWERED = "rate_app_answered"
    }

    object Params {
        const val SEARCH_EVENT_QUERY = "query"
        const val SEARCH_EVENT_YEAR_START = "year_start"
        const val SEARCH_EVENT_YEAR_END = "year_end"
        const val SEARCH_EVENT_MEDIA_TYPE = "media_type"

        const val MEDIA_TITLE = "media_title"
        const val MEDIA_AMAZON_ID = "amazon_id"
        const val MEDIA_IMDB_ID = "imdb_id"
        const val MEDIA_TMDB_ID = "tmdb_id"
        const val MEDIA_FOUND_TITLE = "media_found_title"

        const val FICHE_VIDEO_KEY = "fiche_video_key"

        const val COUNTRY_ID = "country_id"
        const val RATE_APP_ANSWER = "rate_app_answer"
    }

    object ParamsValue {
        const val FILTER_MEDIA_TYPE_ALL = "all"
        const val FILTER_MEDIA_TYPE_MOVIE = "movie"
        const val FILTER_MEDIA_TYPE_SERIES = "series"

        const val ANSWER_YES = "yes"
        const val ANSWER_NEVER = "never"
        const val ANSWER_NOT_NOW = "not_now"
    }

}