package com.stardeux.upprime.tmdbinapp.mapper

import com.stardeux.upprime.media.common.repository.model.ShortMedia
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import java.util.*

class ImdbMediaRequestMapper (private val locale: Locale) {

    fun mapToImdbMediaRequest(shortMedia: ShortMedia): ImdbMediaRequest {
        return with(shortMedia) {
            ImdbMediaRequest(
                imdbId = imdbId,
                amazonId = amazonId,
                amazonReleaseDate = dateAdded,
                name = title,
                locale.language
            )
        }
    }

}