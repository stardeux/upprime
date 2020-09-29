package com.stardeux.upprime.media.fiche.ui.mapper

import android.net.Uri
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class AmazonPlayNativeUriMapper {

    fun getAmazonPlayNativeUri(movieDetails: MovieDetails): Uri {
        return getAmazonPlayNativeUri(movieDetails.amazonId)
    }

    fun getAmazonPlayNativeUri(seriesDetails: SeriesDetails): Uri {
        return getAmazonPlayNativeUri(seriesDetails.amazonId)
    }

    private fun getAmazonPlayNativeUri(amazonId: String): Uri {
        return Uri.parse("intent://watch.amazon.com/watch?asin=$amazonId")
    }
}