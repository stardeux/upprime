package com.stardeux.upprime.media.fiche.ui.mapper

import android.net.Uri
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.SeriesDetails

class AmazonPlayWebUriMapper {

    fun getAmazonPlayWebUri(movieDetails: MovieDetails): Uri {
        return getAmazonPlayWebUri(movieDetails.amazonId)
    }

    fun getAmazonPlayWebUri(seriesDetails: SeriesDetails): Uri {
        return getAmazonPlayWebUri(seriesDetails.amazonId)
    }

    private fun getAmazonPlayWebUri(amazonId: String): Uri {
        return Uri.parse("intent://watch.amazon.com/watch?asin=$amazonId")
    }
}