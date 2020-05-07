package com.stardeux.upprime.latest.ui.mapper

import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media
import com.stardeux.upprime.tmdb.usecase.model.MovieDetails

fun mapToMediaUi(media: Media): MediaUi {
    return MediaUi(
        title = media.title,
        amazonId = media.amazonId,
        imdbId = media.imdbId,
        type = mapToStringId(media.type),
        posterUrl = null
    )
}

fun mapToMediaUi(movieDetails: MovieDetails, shortMediaUi: MediaUi): MediaUi {
    return MediaUi(
        title = movieDetails.title,
        amazonId = shortMediaUi.amazonId,
        imdbId = movieDetails.imdbId,
        type = shortMediaUi.type,
        //TODO dynamically compute base url
        posterUrl = "https://image.tmdb.org/t/p/w500" + movieDetails.posterUrl
    )
}