package com.stardeux.upprime.media.fiche.usecase

import android.net.Uri
import com.stardeux.upprime.core.usecase.IsIntentResolvableUseCase
import com.stardeux.upprime.media.fiche.ui.mapper.AmazonPlayNativeUriMapper
import com.stardeux.upprime.media.fiche.ui.mapper.AmazonPlayWebUriMapper
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class AmazonPlayUriUseCase(
    private val isIntentResolvableUseCase: IsIntentResolvableUseCase,
    private val amazonPlayNativeUriMapper: AmazonPlayNativeUriMapper,
    private val amazonPlayWebUriMapper: AmazonPlayWebUriMapper
) {

    fun getAmazonPlayUri(movieDetails: MovieDetails): Uri {

    }

    fun getAmazonPlayUri(seriesDetails: SeriesDetails): Uri {

    }

    private fun getAmazonPlayUri(amazonId: String): Uri {

    }

}