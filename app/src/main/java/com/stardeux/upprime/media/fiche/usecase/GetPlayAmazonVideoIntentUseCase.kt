package com.stardeux.upprime.media.fiche.usecase

import android.content.Intent
import android.net.Uri
import com.stardeux.upprime.core.usecase.IsIntentResolvableUseCase
import com.stardeux.upprime.media.fiche.ui.mapper.GetPlayAmazonVideoNativeUriMapper
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class GetPlayAmazonVideoIntentUseCase(
    private val isIntentResolvableUseCase: IsIntentResolvableUseCase,
    private val getPlayAmazonVideoNativeUriMapper: GetPlayAmazonVideoNativeUriMapper
) {

    fun getAmazonPlayIntent(tmdbMovieDetails: TmdbMovieDetails): Uri {
        val appDeeplink = getPlayAmazonVideoNativeUriMapper.getAmazonPlayNativeUri(tmdbMovieDetails)
        val intent = makeIntent(appDeeplink)
        return appDeeplink.takeIf { isIntentResolvableUseCase.isResolvable(intent) } ?: Uri.parse("")
    }

    fun getAmazonPlayIntent(seriesDetails: SeriesDetails): Uri {
        val appDeeplink = getPlayAmazonVideoNativeUriMapper.getAmazonPlayNativeUri(seriesDetails)
        val intent = makeIntent(appDeeplink)
        return appDeeplink.takeIf { isIntentResolvableUseCase.isResolvable(intent) } ?: Uri.parse("")
    }

    private fun makeIntent(uri: Uri): Intent {
        return Intent(Intent.ACTION_VIEW, uri)
    }

}