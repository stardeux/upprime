package com.stardeux.upprime.latest.ui.mapper

import com.stardeux.upprime.core.mapper.mapToStringId
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMediaUi(media: Media): MediaUi {
    return MediaUi(
        title = media.title,
        amazonId = media.amazonId,
        imdbId = media.imdbId,
        typeText = mapToStringId(media.type),
        posterUrl = null
    )
}

fun mapToMediaUi(movieDetails: MovieDetails, shortMediaUi: MediaUi): MediaUi {
    return MediaUi(
        title = movieDetails.title,
        amazonId = shortMediaUi.amazonId,
        imdbId = movieDetails.imdbId,
        typeText = shortMediaUi.typeText,
        //TODO dynamically compute base url
        posterUrl = "https://image.tmdb.org/t/p/w500" + movieDetails.posterUrl
    )
}