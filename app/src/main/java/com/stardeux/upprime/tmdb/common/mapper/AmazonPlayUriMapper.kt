package com.stardeux.upprime.tmdb.common.mapper

import android.net.Uri
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class AmazonPlayUriMapper {

    fun getAmazonPlayUri(movieDetails: MovieDetails): Uri {
        return getAmazonPlayUri(movieDetails.amazonId)
    }

    fun getAmazonPlayUri(seriesDetails: SeriesDetails): Uri {
        return getAmazonPlayUri(seriesDetails.amazonId)
    }

    private fun getAmazonPlayUri(amazonId: String): Uri {
        return Uri.parse("intent://watch.amazon.com/watch?asin=$amazonId")
    }
}