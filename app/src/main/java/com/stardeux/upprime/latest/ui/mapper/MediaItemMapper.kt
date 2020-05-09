package com.stardeux.upprime.latest.ui.mapper

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.latest.ui.model.MediaUi
import com.stardeux.upprime.latest.usecase.model.Media
import com.stardeux.upprime.tmdb.movie.usecase.model.MovieDetails

fun mapToMediaUi(media: Media): MediaUi {
    return with(media) {
        MediaUi(
            title = title,
            type = type,
            amazonId = amazonId,
            imdbId = imdbId,
            posterUrl = null
        )
    }
}

fun mapToMediaUi(movieDetails: MovieDetails, shortMediaUi: MediaUi): MediaUi {
    return with(movieDetails) {
        MediaUi(
            title = title,
            type = MediaType.MOVIE,
            amazonId = shortMediaUi.amazonId,
            imdbId = imdbId,
            //TODO dynamically compute base url
            posterUrl = "https://image.tmdb.org/t/p/w500$posterUrl"
        )
    }
}