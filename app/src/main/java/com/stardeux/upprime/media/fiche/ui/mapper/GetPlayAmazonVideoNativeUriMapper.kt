package com.stardeux.upprime.media.fiche.ui.mapper

import android.net.Uri
import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.media.fiche.ui.model.MediaFicheUi
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieDetails
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesDetails

class GetPlayAmazonVideoNativeUriMapper {

    fun getAmazonPlayNativeUri(shortMedia: ShortMedia): Uri {
        return getAmazonPlayNativeUri(shortMedia.amazonId)
    }

    private fun getAmazonPlayNativeUri(amazonId: String): Uri {
        return Uri.parse("intent://watch.amazon.com/watch?asin=$amazonId")
    }
}