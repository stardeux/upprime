package com.stardeux.upprime.core.analytics

import androidx.core.os.bundleOf
import com.stardeux.upprime.core.extension.spaceSeparator
import com.stardeux.upprime.country.ui.model.CountryUi
import com.stardeux.upprime.country.usecase.model.AvailableCountry
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.common.ui.model.MediaItemUi
import com.stardeux.upprime.rate.usecase.RateAppAnswer
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.ui.model.MediaTypeFilter
import com.stardeux.upprime.tmdb.video.ui.model.MediaVideoUi

fun MediaTypeFilter.getTrackingValue(): String {
    return when (this) {
        MediaTypeFilter.ALL -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_ALL
        MediaTypeFilter.MOVIE -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_MOVIE
        MediaTypeFilter.SERIES -> AnalyticsValues.ParamsValue.FILTER_MEDIA_TYPE_SERIES
    }
}

fun ShortMedia.getTrackingParameters(): AnalyticsParams {
    return AnalyticsParams(
        bundleOf(
            AnalyticsValues.Params.MEDIA_TITLE to title,
            AnalyticsValues.Params.MEDIA_IMDB_ID to imdbId,
            AnalyticsValues.Params.MEDIA_AMAZON_ID to amazonId
        ), spaceSeparator(imdbId, amazonId, title)
    )
}

fun MediaVideoUi.getTrackingParameters(): AnalyticsParams {
    return AnalyticsParams(
        bundleOf(
            AnalyticsValues.Params.FICHE_VIDEO_KEY to key
        ), key ?: "no key"
    )
}

fun CountryUi.getTrackingValue(): String {
    return availableCountry.getTrackingValue()
}

fun AvailableCountry.getTrackingValue(): String {
    return when (this) {
        AvailableCountry.UNITED_STATES_AMERICA -> "us"
        AvailableCountry.GREAT_BRITAIN -> "gb"
        AvailableCountry.GERMANY -> "de"
        AvailableCountry.ARGENTINA -> "ar"
        AvailableCountry.AUSTRALIA -> "au"
        AvailableCountry.BRAZIL -> "br"
        AvailableCountry.CANADA -> "ca"
        AvailableCountry.FRANCE -> "fr"
    }
}

fun CountryUi.getTrackingParameters(): AnalyticsParams {
    return AnalyticsParams(
        bundleOf(AnalyticsValues.Params.COUNTRY_ID to getTrackingValue()), getTrackingValue()
    )
}

fun MediaItemUi.getTrackingParameters(): AnalyticsParams {
    return AnalyticsParams(
        bundleOf(
            AnalyticsValues.Params.MEDIA_TITLE to shortMedia.title,
            AnalyticsValues.Params.MEDIA_IMDB_ID to shortMedia.imdbId,
            AnalyticsValues.Params.MEDIA_AMAZON_ID to shortMedia.amazonId,
            AnalyticsValues.Params.MEDIA_TMDB_ID to tmdbId,
            AnalyticsValues.Params.MEDIA_FOUND_TITLE to title
        ), spaceSeparator(shortMedia.title, shortMedia.imdbId, tmdbId)
    )
}

fun RateAppAnswer.getTrackingParameters(): AnalyticsParams {
    val answer = when (this) {
        RateAppAnswer.YES -> AnalyticsValues.ParamsValue.ANSWER_YES
        RateAppAnswer.NEVER -> AnalyticsValues.ParamsValue.ANSWER_NEVER
        RateAppAnswer.NOT_NOW -> AnalyticsValues.ParamsValue.ANSWER_NOT_NOW
    }

    return AnalyticsParams(bundleOf(AnalyticsValues.Params.RATE_APP_ANSWER to answer), answer)
}

fun AmazonSearchRequest.getTrackingParameters(): AnalyticsParams {
    return AnalyticsParams(
        bundleOf(
            AnalyticsValues.Params.SEARCH_EVENT_QUERY to title,
            AnalyticsValues.Params.SEARCH_EVENT_MEDIA_TYPE to mediaTypeFilter.getTrackingValue(),
            AnalyticsValues.Params.SEARCH_EVENT_YEAR_START to yearStart,
            AnalyticsValues.Params.SEARCH_EVENT_YEAR_END to yearEnd
        ), spaceSeparator(
            title, mediaTypeFilter.getTrackingValue(), yearStart.toString(), yearEnd.toString()
        )
    )
}