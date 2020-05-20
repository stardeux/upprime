package com.stardeux.upprime.tmdb.common.mapper

import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.common.request.ImdbMediaRequest
import com.stardeux.upprime.tmdb.movie.usecase.model.TmdbMovieRequest
import com.stardeux.upprime.tmdb.series.usecase.model.TmdbSeriesRequest
import java.util.*

class ImdbMediaRequestMapper {

    fun mapToTmdbSeriesRequest(
        imdbMediaRequest: ImdbMediaRequest,
        tmdbId: TmdbId
    ): TmdbSeriesRequest {
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

    fun mapToTmdbMovieRequest(
        imdbMediaRequest: ImdbMediaRequest,
        tmdbId: TmdbId?
    ): TmdbMovieRequest {
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

}