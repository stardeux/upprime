package com.stardeux.upprime.tmdb.common.request

import com.stardeux.upprime.media.common.repository.model.ShortMedia

fun mapToImdbMediaRequest(shortMedia: ShortMedia): ImdbMediaRequest {
    return with(shortMedia) {
        ImdbMediaRequest(
            imdbId = imdbId,
            amazonId = amazonId,
            amazonReleaseDate = dateAdded,
            name = title
        )
    }
}