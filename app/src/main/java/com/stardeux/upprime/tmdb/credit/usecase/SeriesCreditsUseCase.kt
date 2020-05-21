package com.stardeux.upprime.tmdb.credit.usecase

import com.stardeux.upprime.core.model.MediaType
import com.stardeux.upprime.core.model.TmdbId
import com.stardeux.upprime.tmdb.credit.repository.MediaCreditRepository
import com.stardeux.upprime.tmdb.credit.usecase.model.MediaCredits
import com.stardeux.upprime.tmdb.series.usecase.GetSeriesDetailsUseCase

class SeriesCreditsUseCase (
    private val mediaCreditRepository: MediaCreditRepository,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase
) {

    suspend fun getMediaCredit(tmdbId: TmdbId): MediaCredits {
        return mediaCreditRepository.getCredits(MediaType.SERIES, tmdbId)
    }
}
