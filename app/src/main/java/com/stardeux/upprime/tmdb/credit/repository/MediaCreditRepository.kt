package com.stardeux.upprime.tmdb.credit.repository

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.api.TmdbCreditApi
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits

class MediaCreditRepository(
    private val creditApi: TmdbCreditApi,
    private val mediaCreditMapper: MediaCreditMapper
) {

    suspend fun getCredits(mediaType: MediaType, tmdbId: TmdbId): MediaCredits {
        return mediaCreditMapper.mapToMediaCredits(
            when (mediaType) {
                MediaType.MOVIE -> creditApi.movieCredits(tmdbId)
                MediaType.SERIES -> creditApi.seriesCredits(tmdbId)
            }
        )
    }

}