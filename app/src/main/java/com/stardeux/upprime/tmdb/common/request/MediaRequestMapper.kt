package com.stardeux.upprime.tmdb.common.request

import com.stardeux.upprime.core.model.TmdbId
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

fun mapToTmdbSeriesRequest(imdbMediaRequest: ImdbMediaRequest, tmdbId: TmdbId): TmdbSeriesRequest {
    return with(imdbMediaRequest) {
        TmdbSeriesRequest(
            imdbId = imdbId,
            amazonId = amazonId,
            tmdbId = tmdbId,
            amazonReleaseDate = amazonReleaseDate,
            name = name
        )
    }
}

fun mapToTmdbMovieRequest(imdbMediaRequest: ImdbMediaRequest, tmdbId: TmdbId?): TmdbMovieRequest {
    return with(imdbMediaRequest) {
        TmdbMovieRequest(
            imdbId = imdbId,
            amazonId = amazonId,
            tmdbId = tmdbId,
            amazonReleaseDate = amazonReleaseDate,
            name = name
        )
    }
}