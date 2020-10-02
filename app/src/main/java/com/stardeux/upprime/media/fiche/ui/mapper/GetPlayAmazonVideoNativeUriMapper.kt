package com.stardeux.upprime.media.fiche.ui.mapper

import android.net.Uri
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails

class GetPlayAmazonVideoNativeUriMapper {

    fun getAmazonPlayNativeUri(tmdbMovieDetails: TmdbMovieDetails): Uri {
        return getAmazonPlayNativeUri(tmdbMovieDetails.amazonId)
    }

    fun getAmazonPlayNativeUri(tmdbSeriesDetails: TmdbSeriesDetails): Uri {
        return getAmazonPlayNativeUri(tmdbSeriesDetails.amazonId)
    }

    private fun getAmazonPlayNativeUri(amazonId: String): Uri {
        return Uri.parse("intent://watch.amazon.com/watch?asin=$amazonId")
    }
}